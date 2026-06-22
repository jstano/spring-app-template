# application-services — Agent Rules

**Service layer for use-case orchestration.** Implement your application's business workflows here, mapping between domain entities and API contracts.

## What Belongs Here
- Use-case orchestration services (one service class per aggregate root)
- Transaction boundary definitions
- Domain entity → DTO mapping logic (MapStruct mapper interfaces)

## Layer Boundary
- May import: `domain`, `application-contracts`
- Must NOT import: any adapter module
- Inject repositories as interfaces (ports) — never depend on concrete JPA repository implementations directly

## Conventions
- Annotate service classes with `@Service`
- Annotate public methods with `@Transactional` (read-only queries get `@Transactional(readOnly = true)`)
- Map domain entities to `application-contracts` DTOs here — not in adapters or domain
- One service class per aggregate root; keep methods focused on a single use case
- Define mappers as interfaces annotated with `@Mapper(componentModel = "spring")`; MapStruct generates the implementation:
  ```java
  @Mapper(componentModel = "spring")
  public interface PersonResponseMapper {
    PersonResponse toResponse(Person person);
  }
  ```
- Repository interfaces are defined in `domain` — inject them here as constructor parameters

## Testing
- Use the real MapStruct-generated mapper implementation (e.g. `new PersonResponseMapperImpl()`) in service tests — never mock mappers
- Every mapper must have a null-safety test: call each mapping method with `null` and assert the result is `null`. This exercises the MapStruct null-guard code that otherwise goes uncovered (see `PersonResponseMapperTest` as the canonical example)

## Anti-Patterns
- No HTTP types (`HttpServletRequest`, `ResponseEntity`, `@RequestBody`, etc.)
- No direct SQL or JPQL queries — use repository interfaces
- No business logic that belongs inside a domain entity method
- No `@RestController` or other adapter-layer annotations
