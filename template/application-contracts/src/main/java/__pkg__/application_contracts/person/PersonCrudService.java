package {{ group_id }}.{{ app_package }}.application_contracts.person;

import java.util.List;
import java.util.UUID;

public interface PersonCrudService {
  PersonResponse getPerson(UUID id);

  List<PersonResponse> getPeople();
}
