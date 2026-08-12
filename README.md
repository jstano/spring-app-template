# spring-app-template

A [Copier](https://copier.readthedocs.io/) template that scaffolds a production-grade Spring Boot microservice using hexagonal (ports & adapters) architecture with PostgreSQL, Flyway, and JWT security.

## Prerequisites

- [Copier](https://copier.readthedocs.io/en/stable/#installation) (`pip install copier`)
- Python 3 (required by the post-generation script)
- Java 21
- Gradle

## Usage

Generate a new project from GitHub:

```bash
copier copy --trust gh:jstano/spring-app-template <output-dir>
```

Or from a local clone of this repo:

```bash
copier copy --trust /path/to/spring-app-template <output-dir>
```

`--trust` is required: this template runs post-generation tasks (see below) to rename the `__pkg__` placeholder directories and build the generated project, and Copier treats `_tasks` as a potentially unsafe feature that it won't run without `--trust`. Without it, the copy completes silently, every package directory is left named `__pkg__`, and the project is never built.

Copier will prompt you for the variables below, then automatically:
1. Rename package directories to match your chosen group ID and package name (`rename_pkg.py`).
2. Run `./gradlew clean build spotlessApply --write-locks` in the generated project to verify it builds, apply Spotless formatting, and write Gradle dependency locks.

## Template Variables

| Variable | Description | Default |
|---|---|---|
| `project_name` | Kebab-case Gradle root project name (e.g. `my-service`) | _(required)_ |
| `group_id` | Maven group ID / Java base package (e.g. `com.example`) | _(required)_ |
| `app_package` | Application sub-package, one lowercase word (e.g. `myapp`) | _(required)_ |
| `db_name` | PostgreSQL database name | `project_name` with `-` replaced by `_` |
| `db_username` | PostgreSQL username | `postgres` |
| `db_password` | PostgreSQL password | _(empty)_ |
| `jwt_issuer_uri` | JWT issuer URI | `http://localhost:8080/realms/master` |

## Generated Project Structure

```
<output-dir>/
├── domain/                  # Core domain model and business rules
├── application-contracts/   # Ports (interfaces) and DTOs
├── application-services/    # Use case implementations
├── adapter-rest-api/        # REST controllers (inbound adapter)
├── spring-configuration/    # Spring beans and security config
├── spring-launcher/         # Application entry point
└── schema/                  # Flyway SQL migrations
```

All Java source files are placed under `<group_id>.<app_package>` (e.g. `com.example.myapp`). The post-generation script `rename_pkg.py` handles this automatically by replacing the `__pkg__` placeholder directories with the real package path.
