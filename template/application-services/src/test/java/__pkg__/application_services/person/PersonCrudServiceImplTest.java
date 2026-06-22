package {{ group_id }}.{{ app_package }}.application_services.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import {{ group_id }}.{{ app_package }}.application_contracts.person.PersonResponse;
import {{ group_id }}.{{ app_package }}.domain.person.Person;
import {{ group_id }}.{{ app_package }}.domain.person.PersonId;
import {{ group_id }}.{{ app_package }}.domain.person.PersonRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonCrudServiceImplTest {
  private PersonRepository personRepository;
  private PersonResponseMapper personResponseMapper;
  private PersonCrudServiceImpl personCrudService;

  @BeforeEach
  void setUp() {
    personRepository = mock(PersonRepository.class);
    personResponseMapper = new PersonResponseMapperImpl();
    personCrudService = new PersonCrudServiceImpl(personRepository, personResponseMapper);
  }

  @Test
  void gettingAPersonShouldFetchFromRepositoryAndMapToResponse() {
    UUID lookupId = UUID.randomUUID();
    PersonId personId = new PersonId(lookupId);
    Person person = new Person("Alice", LocalDate.of(1990, 1, 15));
    PersonResponse expectedResponse =
        new PersonResponse(person.getId().value(), "Alice", LocalDate.of(1990, 1, 15), null);

    when(personRepository.get(personId)).thenReturn(person);

    PersonResponse result = personCrudService.getPerson(lookupId);

    assertThat(result).isEqualTo(expectedResponse);
    verify(personRepository).get(personId);
  }

  @Test
  void gettingAllPeopleShouldFetchFromRepositoryAndMapToResponseList() {
    Person person1 = new Person("Alice", LocalDate.of(1990, 1, 15));
    Person person2 = new Person("Bob", LocalDate.of(1985, 6, 20));

    when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

    List<PersonResponse> result = personCrudService.getPeople();

    assertThat(result)
        .containsExactly(
            new PersonResponse(person1.getId().value(), "Alice", LocalDate.of(1990, 1, 15), null),
            new PersonResponse(person2.getId().value(), "Bob", LocalDate.of(1985, 6, 20), null));
    verify(personRepository).findAll();
  }

  @Test
  void gettingAllPeopleShouldReturnEmptyListWhenNoPersonsExist() {
    when(personRepository.findAll()).thenReturn(Arrays.asList());

    List<PersonResponse> result = personCrudService.getPeople();

    assertThat(result).isEmpty();
    verify(personRepository).findAll();
  }
}
