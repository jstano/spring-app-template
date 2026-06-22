package {{ group_id }}.{{ app_package }}.rest_api.person;

import {{ group_id }}.{{ app_package }}.application_contracts.person.PersonCrudService;
import {{ group_id }}.{{ app_package }}.application_contracts.person.PersonResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonController {
  private final PersonCrudService personCrudService;

  public PersonController(PersonCrudService personCrudService) {
    this.personCrudService = personCrudService;
  }

  @GetMapping
  public List<PersonResponse> getPeople() {
    return personCrudService.getPeople();
  }

  @GetMapping("/{id}")
  public PersonResponse getPerson(@PathVariable UUID id) {
    return personCrudService.getPerson(id);
  }
}
