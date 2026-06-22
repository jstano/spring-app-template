# application-contracts — Agent Rules

**Define your API contracts here.** All request/response types flow through this module; it's the public interface of your application layer.

## What Belongs Here
- Java `record` DTOs for API request and response bodies (e.g., `CreateRequest`, `Response`)
- Command and query objects passed between adapters and application services
- Shared interface definitions used across layers
- Validation annotations for API inputs

## Layer Boundary
This module must be **dependency-free except plain Java** — no Spring, no JPA, no domain entity imports.
Both adapters and application-services depend on this module; it must not depend on them.

## Conventions
- Always use Java `record` — never mutable classes for data transfer
- Name types clearly and consistently: `{Verb}{Aggregate}Request` / `{Aggregate}Response`
- Use `@Nullable` / `@NotNull` (from `jakarta.annotation`) for documentation where useful
- UUIDs flow through contracts as `UUID` (not `String`)

## Anti-Patterns
- No business logic of any kind
- No mutable fields or setters
- Never expose JPA `@Entity` objects directly as contract types
- No Spring annotations (`@Component`, `@Service`, etc.)
