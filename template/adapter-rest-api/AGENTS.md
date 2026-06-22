# adapter-rest-api — Agent Rules

**Customize the API path and scope for your domain.** This adapter template provides a REST endpoint layer; replace the path and use-case examples with your application's requirements.

## What Belongs Here
- `@RestController` classes for REST endpoints
- Route definitions, request validation, HTTP response shaping

## Layer Boundary
- Depends on `application-contracts` for all request/response types
- Imports only `application-contracts`

## Conventions
- Annotate incoming request bodies with `@Valid` — always
- Keep controllers thin: validate input, call one service method, return response
- Use `application-contracts` record types as `@RequestBody` and return values
- Return appropriate HTTP status codes (`201 Created` for POST, `204 No Content` for DELETE, etc.)
- Controllers are single-responsibility — one controller per aggregate root or resource type

## Testing

- Use `@WebMvcTest(<ControllerClass>.class)` to test controllers in isolation — loads only the web layer
- Mock all service dependencies with `@MockitoBean` (not the deprecated `@MockBean`)
- Use `MockMvc` for HTTP testing: `mockMvc.perform(post(...))`, `andExpect(status().isCreated())`, etc.
- Test request validation, response mapping, and HTTP status codes — not business logic
- Example:
  ```java
  @WebMvcTest(PersonController.class)
  class PersonControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockitoBean private PersonCrudService service;
    
    @Test
    void creatingAPersonWithValidDataShouldReturn201() throws Exception {
      var response = new PersonResponse(/* ... */);
      when(service.createPerson(any())).thenReturn(response);
      
      mockMvc.perform(post("/persons")
        .contentType(APPLICATION_JSON)
        .content("""{"name":"Alice"}"""))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists());
    }
  }
  ```

## Anti-Patterns
- No business logic in controller methods
- No direct database access or repository injection
- No `@Transactional` in controllers
- No dependencies on other adapter modules
- Never wrap return values in `ResponseEntity` — use `@ResponseStatus` on the method or controller class to declare HTTP status codes instead
