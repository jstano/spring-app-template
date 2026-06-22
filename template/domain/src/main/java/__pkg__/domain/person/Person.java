package {{ group_id }}.{{ app_package }}.domain.person;

import com.stano.domain_jpa.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
public class Person extends AbstractEntity<PersonId> {
  private String name;
  private LocalDate birthDate;
  private String address;

  public Person() {}

  public Person(String name, LocalDate birthDate) {
    this.name = name;
    this.birthDate = birthDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  protected PersonId typedId(UUID value) {
    return new PersonId(value);
  }
}
