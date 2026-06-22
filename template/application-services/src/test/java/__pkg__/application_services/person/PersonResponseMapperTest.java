package {{ group_id }}.{{ app_package }}.application_services.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import {{ group_id }}.{{ app_package }}.domain.person.Person;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class PersonResponseMapperTest {
  @Test
  public void testNull() {
    assertNull(new PersonResponseMapperImpl().toResponse(null));
    assertNull(new PersonResponseMapperImpl().map(null));
  }

  @Test
  void mappingAPersonWithAnAddressShouldIncludeAddressInResponse() {
    Person person = new Person("Alice", LocalDate.of(1990, 1, 15));
    person.setAddress("123 Main St");

    var response = new PersonResponseMapperImpl().toResponse(person);

    assertThat(response.address()).isEqualTo("123 Main St");
  }
}
