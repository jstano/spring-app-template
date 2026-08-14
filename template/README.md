# {{ project_name }}

A Spring Boot microservice built with hexagonal (ports & adapters) architecture, PostgreSQL, Flyway, and JWT security.

## Prerequisites

- Java 21
- Gradle (or use the included `./gradlew` wrapper)
- PostgreSQL (running locally with a `{{ db_name }}` database, or point `DB_URL` at another instance)

## Configuration

Environment variables are read from a `.env` file at the project root. Copy the local example to get started:

```bash
cp .env-local .env
```

| Variable | Description |
|---|---|
| `DB_URL` | JDBC URL of the PostgreSQL database |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `ISSUER_URL` | JWT issuer URI used to validate incoming tokens |

`.env-example` documents the same variables for non-local environments.

## Building & Running

```bash
./gradlew build       # compile and run all tests
./gradlew bootRun      # start the application
```

The application applies Flyway migrations from `spring-configuration/db/migration/` automatically on startup.

## Project Structure

```
{{ project_name }}/
├── domain/                  # Core domain model and business rules
├── application-contracts/   # Ports (interfaces) and DTOs
├── application-services/    # Use case implementations
├── adapter-rest-api/        # REST controllers (inbound adapter)
├── spring-configuration/    # Spring beans, security config, and DB migrations
├── spring-launcher/         # Application entry point
└── schema/                  # Database schema module
```

All Java source lives under the `{{ group_id }}.{{ app_package }}` package. See `AGENTS.md` for detailed architecture, coding conventions, and testing guidelines.

## Common Gradle Commands

```bash
./gradlew spotlessCheck     # check code style compliance
./gradlew spotlessApply     # auto-fix code style issues
./gradlew test              # run unit and integration tests
./gradlew clean build       # clean rebuild
```
