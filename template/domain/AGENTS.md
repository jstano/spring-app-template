# domain — Agent Rules

**This module contains your domain model.** Define your aggregate roots, entities, and value objects here; replace the examples below with your actual domain.

## What Belongs Here
- JPA `@Entity` classes (aggregate roots and entities specific to your domain)
- Domain value objects and enumerations
- Domain-specific exceptions
- Business invariant enforcement (inside entity methods)

## Layer Boundary
This module must have **no knowledge of Spring, HTTP, or the application layer**.
- No `@Service`, `@Component`, `@RestController`, or any Spring stereotype
- No imports from `application-services`, `application-contracts`, or any adapter module
- No HTTP types (`HttpServletRequest`, `ResponseEntity`, etc.)

## Conventions
- Use `jakarta.persistence.*` for all JPA annotations — never `javax.persistence.*`
- All `@Id` fields are `UUID` and must be populated with a UUIDv7 value before persist
- Enforce business rules inside entity methods, not in service classes
- Use `@Column(nullable = false)` to mirror database constraints

## Testing

Every entity must have a `<Entity>RepositoryTest` that covers:
- Saving and retrieving by ID (all constructor-set fields)
- Each mutable field: set the value on a saved entity, flush + clear, re-fetch, assert the new value persists
- Updating a previously-set field to a new value and confirming the change persists

Use `@JpaTest` + `BaseJpaTest`, flush + clear between writes and reads, and AssertJ assertions.

## Anti-Patterns
- No service-layer orchestration logic inside entities
- No DTO types in this module — entities are never serialized directly to the API
- No direct repository calls from within entity methods
- No `@Transactional` annotations in this module
