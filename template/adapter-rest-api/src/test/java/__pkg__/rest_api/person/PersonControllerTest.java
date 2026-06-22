package {{ group_id }}.{{ app_package }}.rest_api.person;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import {{ group_id }}.{{ app_package }}.application_contracts.person.PersonCrudService;
import {{ group_id }}.{{ app_package }}.application_contracts.person.PersonResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonController.class)
class PersonControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockitoBean private PersonCrudService personCrudService;

  @Test
  void gettingAllPeopleReturnsEmptyListWhenNoPersonsExist() throws Exception {
    when(personCrudService.getPeople()).thenReturn(Arrays.asList());
    mockMvc.perform(get("/people")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  void gettingAllPeopleReturnsAllPersonsInList() throws Exception {
    UUID id1 = UUID.randomUUID();
    UUID id2 = UUID.randomUUID();
    PersonResponse person1 = new PersonResponse(id1, "Alice", LocalDate.of(1990, 1, 15), null);
    PersonResponse person2 = new PersonResponse(id2, "Bob", LocalDate.of(1985, 6, 20), null);
    when(personCrudService.getPeople()).thenReturn(Arrays.asList(person1, person2));
    mockMvc
        .perform(get("/people"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(id1.toString())))
        .andExpect(jsonPath("$[0].name", is("Alice")))
        .andExpect(jsonPath("$[0].birthDate", is("1990-01-15")))
        .andExpect(jsonPath("$[1].id", is(id2.toString())))
        .andExpect(jsonPath("$[1].name", is("Bob")))
        .andExpect(jsonPath("$[1].birthDate", is("1985-06-20")));
  }

  @Test
  void gettingAPersonByIdReturnsMatchingPerson() throws Exception {
    UUID id = UUID.randomUUID();
    PersonResponse person = new PersonResponse(id, "Charlie", LocalDate.of(1992, 3, 10), null);
    when(personCrudService.getPerson(id)).thenReturn(person);
    mockMvc
        .perform(get("/people/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(id.toString())))
        .andExpect(jsonPath("$.name", is("Charlie")))
        .andExpect(jsonPath("$.birthDate", is("1992-03-10")));
  }
}
