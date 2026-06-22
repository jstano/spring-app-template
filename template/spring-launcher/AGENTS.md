# spring-launcher — Agent Rules

**Application entry point and composition root.** Customize the main class name and seed-data logic for your domain.

## What Belongs Here
- Main class (`@SpringBootApplication`) — rename from template default
- `SeedDataLoader` — `CommandLineRunner` bean for loading demo/seed data on startup (customize for your domain)
- `UuidGenerator` — factory for generating UUIDv7 primary keys
- Exception-to-HTTP-status mapping (`@ControllerAdvice`)
- Global error handling and serialization configuration

## Layer Boundary
- May import any module (it is the composition root)
- Wires everything together but **contains no business logic**

## Conventions
- Always generate UUIDs via `UuidGenerator` — never call `UUID.randomUUID()` directly anywhere in the project
- Seed data belongs in `SeedDataLoader`, not scattered across tests or config classes
- Keep this module minimal — it should only bootstrap and compose the application

## Anti-Patterns
- No business logic of any kind
- No `@RestController` or service beans that belong in other modules
- No domain entity definitions
- Seed data must not run in production — guard `SeedDataLoader` with a profile or environment check if needed
