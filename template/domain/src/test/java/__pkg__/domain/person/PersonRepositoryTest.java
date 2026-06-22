package {{ group_id }}.{{ app_package }}.domain.person;

import static org.assertj.core.api.Assertions.assertThat;

import com.stano.domain_jpa.BaseJpaTest;
import com.stano.domain_jpa.JpaTest;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@JpaTest
class PersonRepositoryTest extends BaseJpaTest {
  @Autowired private PersonRepository personRepository;

  @Test
  void savingAPersonShouldPersistAndBeRetrievableById() {
    var person = new Person("Alice", LocalDate.of(1990, 1, 15));
    personRepository.add(person);
    entityManager.flush();
    entityManager.clear();

    var fetched = personRepository.get(person.getId());

    assertThat(fetched.getName()).isEqualTo("Alice");
    assertThat(fetched.getBirthDate()).isEqualTo(LocalDate.of(1990, 1, 15));
  }

  @Test
  void findingAllPersonsShouldReturnAllSavedPersons() {
    personRepository.add(new Person("Alice", LocalDate.of(1990, 1, 15)));
    personRepository.add(new Person("Bob", LocalDate.of(1985, 6, 20)));
    entityManager.flush();
    entityManager.clear();

    var all = personRepository.findAll();

    assertThat(all).hasSize(2);
  }

  @Test
  void checkingExistenceOfSavedPersonShouldReturnTrue() {
    var person = new Person("Charlie", LocalDate.of(1992, 3, 10));
    personRepository.add(person);
    entityManager.flush();

    assertThat(personRepository.exists(person.getId())).isTrue();
  }

  @Test
  void updatingBirthDateShouldPersistTheNewDate() {
    var person = new Person("Diana", LocalDate.of(1995, 4, 22));
    personRepository.add(person);
    entityManager.flush();
    entityManager.clear();

    var fetched = personRepository.get(person.getId());
    fetched.setBirthDate(LocalDate.of(2000, 7, 4));
    entityManager.flush();
    entityManager.clear();

    var updated = personRepository.get(person.getId());
    assertThat(updated.getBirthDate()).isEqualTo(LocalDate.of(2000, 7, 4));
  }

  @Test
  void settingAddressShouldPersistAndBeRetrievable() {
    var person = new Person("Eve", LocalDate.of(1988, 9, 30));
    personRepository.add(person);
    entityManager.flush();
    entityManager.clear();

    var fetched = personRepository.get(person.getId());
    fetched.setAddress("123 Main St");
    entityManager.flush();
    entityManager.clear();

    var updated = personRepository.get(person.getId());
    assertThat(updated.getAddress()).isEqualTo("123 Main St");
  }

  @Test
  void updatingAddressShouldPersistTheNewAddress() {
    var person = new Person("Frank", LocalDate.of(1975, 11, 5));
    person.setAddress("456 Oak Ave");
    personRepository.add(person);
    entityManager.flush();
    entityManager.clear();

    var fetched = personRepository.get(person.getId());
    fetched.setAddress("789 Pine Rd");
    entityManager.flush();
    entityManager.clear();

    var updated = personRepository.get(person.getId());
    assertThat(updated.getAddress()).isEqualTo("789 Pine Rd");
  }
}
