# spring-configuration — Agent Rules

**Infrastructure and configuration — the composition layer.** Replace application-specific settings (security roles, feature flags) with your own.

## What Belongs Here
- `@Configuration` bean definitions (DataSource, security, async executor, etc.)
- Security configuration (role-based access rules, authentication mechanism)
- Flyway versioned SQL migration files (`src/main/resources/db/migration/V*.sql`)
- Cross-cutting infrastructure setup (Jackson, CORS, OpenAPI, etc.)

## Layer Boundary
- May import any module for wiring purposes
- Contains no business logic — only infrastructure and configuration

## Conventions
- **Flyway owns the schema** — every schema change requires a new versioned migration file (`V{n}__{description}.sql`); never modify existing migration files
- `spring.jpa.hibernate.ddl-auto` must remain `validate` — never change it
- Use `application.yml` (not `.properties`) for configuration; use environment variable placeholders for secrets (`${DB_PASSWORD}`)
- API access restrictions are enforced here via security config — adapters trust this

## Anti-Patterns
- No business logic in `@Configuration` classes
- Never use `ddl-auto=create`, `update`, or `create-drop`
- Never hardcode passwords, secrets, or connection strings — use environment variables
- Never edit already-applied Flyway migration files
