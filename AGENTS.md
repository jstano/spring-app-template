# Spring App Template — Agent Rules

**This is a template project.** Customize these rules and examples for your domain and use cases. The structure and patterns are stable; the specific entity names, adapter types, and conventions should be adjusted to match your application.

## Architecture
Hexagonal (ports & adapters) + Domain-Driven Design.
Dependency flow: `adapters → application-contracts ← application-services → domain`

No adapter module may import another adapter module. No domain module may import application or adapter modules.

## Tech Stack
- Java 21, Spring Boot 4.x
- **Always use `jakarta.*` — never `javax.*`** (Spring Boot 4 requires Jakarta EE 11)
- Gradle Kotlin DSL (`build.gradle.kts`)
- PostgreSQL 18, Flyway (schema migrations only — `ddl-auto=validate`)
- Virtual threads enabled (`spring.threads.virtual.enabled=true`)

## Global Conventions
- All primary keys are UUIDv7 — use `UuidGenerator` from `spring-launcher`; never call `UUID.randomUUID()` directly
- Use Java `record` for all DTOs — no mutable POJOs for data transfer
- Shared API types live in `application-contracts`; do not duplicate them in adapters
- Schema changes go in versioned Flyway SQL files in `spring-configuration` — never bypass Flyway

## Java Style

**Naming**
- Classes/interfaces/records: `PascalCase`
- Methods and variables: `camelCase`
- Constants (`static final`): `SCREAMING_SNAKE_CASE`
- Packages: lowercase, no underscores
- Boolean methods: prefix with `is`, `has`, or `can`

**Method & class length**
- Methods: ≤20 lines — extract if longer
- Classes: ≤200 lines — split by responsibility if longer
- One public class per file; one reason to change per class

**Access modifiers**
- Fields: always `private`
- Prefer the narrowest modifier that works — avoid `public` unless part of an exposed API
- No unnecessary getters/setters; use records or constructors for immutable state

**Formatting**
- Opening braces on the same line (`K&R` style)
- Max line length: 120 characters
- One blank line between methods; no blank line after opening brace or opening class declaration
- No trailing whitespace

## Anti-Patterns
- Never use `ddl-auto=create`, `update`, or `create-drop`
- Never hardcode credentials — use environment variables
- Never mix concerns across layer boundaries (e.g., no business logic in controllers, no HTTP types in domain)
- Never skip `@Valid` on incoming REST request bodies
