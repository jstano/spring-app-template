# adapter-rest-api — Agent Rules

**Customize the API path and scope for your domain.** This adapter template provides a REST endpoint layer; replace the path and use-case examples with your application's requirements.

## What Belongs Here
- `@RestController` classes for REST endpoints
- Route definitions, request validation, HTTP response shaping
- Exception-to-HTTP-status mapping (`@ControllerAdvice`)
- Global error handling and serialization configuration

## Layer Boundary
- Depends on `application-contracts` for all request/response types
- Imports only `application-services` and `application-contracts`

## Conventions
- Annotate incoming request bodies with `@Valid` — always
- Keep controllers thin: validate input, call one service method, return response
- Use `application-contracts` record types as `@RequestBody` and return values
- Return appropriate HTTP status codes (`201 Created` for POST, `204 No Content` for DELETE, etc.)
- Controllers are single-responsibility — one controller per aggregate root or resource type

## Anti-Patterns
- No business logic in controller methods
- No direct database access or repository injection
- No `@Transactional` in controllers
- No dependencies on other adapter modules
