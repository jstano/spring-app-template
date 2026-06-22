# Spring App Template — Agent Rules

This is a monolithic application using hexagonal (ports & adapters) architecture with Domain-Driven Design principles.

## Architecture

**Layering** (dependency flow):
- `rest-adapter` (HTTP controllers, request/response mapping) → `application-contracts` (DTOs, service interfaces) ← `application-services` (business logic) → `domain` (entities, repositories)

**Invariants:**
- No adapter imports another adapter. No domain imports application or adapters. No layer above skips intermediaries.
- All HTTP types stay in `rest-adapter`. All domain types stay in `domain`. Shared contracts live in `application-contracts`.

## Tech Stack

- Java 21, Spring Boot 4.x
- **Always use `jakarta.*` — never `javax.*`** (Spring Boot 4 requires Jakarta EE 11)
- Gradle Kotlin DSL (`build.gradle.kts`)
- PostgreSQL 18, Flyway for schema migrations
- `spring.jpa.hibernate.ddl-auto=validate` (never use `create`, `update`, or `create-drop`)
- Virtual threads enabled (`spring.threads.virtual.enabled=true`)

## Coding Conventions

### Package Naming
- Root: `{{ group_id }}.{{ app_package }}`
- Second level: entity aggregate name (e.g., `person`, `order`)
- Third level (optional): layer/concern (e.g., `api`, `service`, `repository`)

Examples:
- `{{ group_id }}.{{ app_package }}.person.api` — REST controller for Person
- `{{ group_id }}.{{ app_package }}.person` — domain entity and value objects
- `{{ group_id }}.{{ app_package }}.person.service` — application service

### Naming Conventions
- Classes/interfaces/records: `PascalCase`
- Methods and variables: `camelCase`
- Constants (`static final`): `SCREAMING_SNAKE_CASE`
- Boolean methods: prefix with `is`, `has`, or `can`

### Code Style (enforced by spotless + googleJavaFormat)
- 2-space indentation
- LF line endings, UTF-8 encoding
- No trailing whitespace, final newline required
- Max line length: 100 characters
- Opening braces on the same line (K&R style)
- No blank line after the opening class `{`
- One blank line between top-level class members
- Formatting is automatic via `./gradlew spotlessApply`; disputes settled by googleJavaFormat, not opinion

### Method & Class Length
- Methods: ≤20 lines — extract helpers if longer
- Classes: ≤200 lines — split by responsibility if longer
- One public class per file; one reason to change per class

### Access Modifiers
- Fields: always `private`
- Classes: `public` only if exposed as API; otherwise `package-private`
- Methods: use the narrowest modifier that works
- No unnecessary getters/setters; use records or constructors for immutable data

## Data & Persistence

- All primary keys: UUIDv7 (never call `UUID.randomUUID()` directly)
- DTOs: Java `record` — no mutable POJOs for data transfer
- Entity ID types: extend `EntityId` from `msp-domain-jpa-starter`
- Entities: extend `AbstractEntity` from `msp-domain-jpa-starter`
- Schema migrations: versioned Flyway SQL in `spring-configuration/db/migration/`

## Testing

- **Class naming:** `<Subject>Test` (e.g., `PersonRepositoryTest`, `PersonServiceTest`)
- **Method naming:** Long descriptive camelCase sentences (e.g., `creatingAPersonWithValidDataShouldPersistSuccessfully()`)
- **Assertions:** Primary library is AssertJ (`assertThat(...).isEqualTo(...)`)
- **Test frameworks:** JUnit 5 + Mockito for unit tests; integration tests use real database when testing persistence
- **Never use deprecated APIs** — use `@MockitoBean` (Spring Boot 4.x), not the deprecated `@MockBean`

### REST Adapter Testing (adapter-rest-api)

- Use `@WebMvcTest` to test controllers in isolation — loads only the web layer without full app context
- Mock all service dependencies via `@MockBean`
- Use `MockMvc` for HTTP testing: `mockMvc.perform(post(...))`, `andExpect(status().isOk())`, etc.
- Test request validation (`@Valid` constraints), response mapping, and HTTP semantics — not business logic
- Example:
  ```java
  @WebMvcTest(PersonController.class)
  class PersonControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private PersonCrudService service;

    @Test
    void creatingAPersonWithValidDataShouldReturn201() throws Exception {
      mockMvc.perform(post("/persons")
        .contentType(APPLICATION_JSON)
        .content("""{"name":"Alice"}"""))
        .andExpect(status().isCreated());
    }
  }
  ```

## Common Gradle Commands

```bash
# Code formatting
./gradlew spotlessCheck     # Check code style compliance
./gradlew spotlessApply     # Auto-fix code style issues

# Building & Testing
./gradlew build             # Compile and run all tests
./gradlew test              # Run unit and integration tests
./gradlew clean             # Remove build artifacts
./gradlew clean build       # Clean rebuild

# Development
./gradlew bootRun           # Start the Spring Boot application
./gradlew -t build          # Continuous build on file changes

# Dependency Management
./gradlew dependencies      # Show dependency tree
./gradlew dependencyUpdates # Check for outdated dependencies (if plugin enabled)
```

## Anti-Patterns

- Never use `ddl-auto=create`, `update`, or `create-drop`
- Never hardcode credentials — use environment variables or `.properties` files
- Never mix concerns: no business logic in controllers, no HTTP types in domain, no SQL in services
- Never skip `@Valid` on incoming REST request bodies
- Never create standalone UUID wrappers — use `EntityId` and `AbstractEntity`
