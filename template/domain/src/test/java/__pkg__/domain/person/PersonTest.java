package {{ group_id }}.{{ app_package }}.domain.person;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class PersonTest {
  @Test
  void creatingAPersonShouldStoreNameAndBirthDate() {
    Person person = new Person("John Doe", LocalDate.of(1990, 5, 15));

    assertThat(person.getName()).isEqualTo("John Doe");
    assertThat(person.getBirthDate()).isEqualTo(LocalDate.of(1990, 5, 15));
  }

  @Test
  void personIdShouldWrapUuid() {
    UUID uuid = UUID.randomUUID();
    PersonId personId = new PersonId(uuid);

    assertThat(personId).isNotNull();
  }

  @Test
  void updatingPersonNameShouldReflectNewValue() {
    Person person = new Person("John Doe", LocalDate.of(1990, 5, 15));

    person.setName("Jane Doe");

    assertThat(person.getName()).isEqualTo("Jane Doe");
  }

  @Test
  void personNoArgsConstructorShouldProduceNonNullInstance() {
    Person person = new Person();

    assertThat(person).isNotNull();
  }
}
