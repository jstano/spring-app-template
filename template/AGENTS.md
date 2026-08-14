# Spring App Template — Agent Rules

This is a monolithic application using hexagonal (ports & adapters) architecture with Domain-Driven Design principles.

## Architecture

**Layering** (dependency flow):
- `rest-adapter` (HTTP controllers, request/response mapping) → `application-contracts` (DTOs, service interfaces) ← `application-services` (business logic) → `domain` (entities, repositories)

**Invariants:**
- No adapter imports another adapter. No domain imports application or adapters. No layer above skips intermediaries.
- All HTTP types stay in `rest-adapter`. All domain types stay in `domain`. Shared contracts live in `application-contracts`.

## AI Working Directory (`.ai/`)

Working files for AI agents live here, separate from application source. It is not runtime code.

- `.ai/context/` — Per-feature or per-task context docs (notes on an in-flight feature, a module's quirks, decisions scoped to one area). Supplements the project-wide rules in this file — check for a relevant file here before starting work that touches an area it covers.
- `.ai/plans/` — Implementation plans. Before starting non-trivial work (multi-file changes, new features), write a short plan here describing the approach, then implement against it.
- `.ai/research/` — Findings from exploration/research tasks (investigating a library, tracing a bug, evaluating an approach). Record findings here so they persist across sessions instead of being lost.
- `.ai/scratch/` — Disposable working files (drafts, temp notes, exploratory output). Gitignored — never rely on anything here surviving or being reviewed.

Rules:
- `.ai/context/`, `.ai/plans/`, and `.ai/research/` are committed to git — write for the next agent, not just yourself, and keep them current or delete them when stale.
- Never put source code, config, or anything the application depends on inside `.ai/` — it's documentation and planning only.

## Tech Stack

- Java 21, Spring Boot 4.x
- **Always use `jakarta.*` — never `javax.*`** (Spring Boot 4 requires Jakarta EE 11)
- Gradle Kotlin DSL (`build.gradle.kts`)
- PostgreSQL 18, Flyway for schema migrations
- Virtual threads enabled (`spring.threads.virtual.enabled=true`)

## Coding Conventions

### Package Naming
- Root: `{{ group_id }}.{{ app_package }}`
- Second level (in each module): module identifier (e.g., `domain`, `rest_api`, `application_services`, `application_contracts`)
- Third level: entity aggregate name (e.g., `person`, `order`)

Examples:
- `{{ group_id }}.{{ app_package }}.rest_api.person` — REST controller for Person
- `{{ group_id }}.{{ app_package }}.domain.person` — domain entity and value objects
- `{{ group_id }}.{{ app_package }}.application_services.person` — application service
- `{{ group_id }}.{{ app_package }}.application_contracts.person` — DTOs and service interfaces

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
./gradlew --write-locks     # Regenerate dependency lock files after changing dependencies
```
