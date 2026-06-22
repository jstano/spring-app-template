package {{ group_id }}.{{ app_package }}.application_services.person;

import com.stano.domain_jpa.id.EntityId;
import {{ group_id }}.{{ app_package }}.application_contracts.person.PersonResponse;
import {{ group_id }}.{{ app_package }}.domain.person.Person;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonResponseMapper {
  PersonResponse toResponse(Person person);

  default UUID map(EntityId id) {
    return id == null ? null : id.value();
  }
}
