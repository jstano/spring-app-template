# application-services — Agent Rules

**Service layer for use-case orchestration.** Implement your application's business workflows here, mapping between domain entities and API contracts.

## What Belongs Here
- Use-case orchestration services (one service class per aggregate root)
- Transaction boundary definitions
- Domain entity → DTO mapping logic
- Repository interface definitions (ports)

## Layer Boundary
- May import: `domain`, `application-contracts`
- Must NOT import: any adapter module
- Inject repositories as interfaces (ports) — never depend on concrete JPA repository implementations directly

## Conventions
- Annotate service classes with `@Service`
- Annotate public methods with `@Transactional` (read-only queries get `@Transactional(readOnly = true)`)
- Map domain entities to `application-contracts` DTOs here — not in adapters or domain
- One service class per aggregate root; keep methods focused on a single use case

## Anti-Patterns
- No HTTP types (`HttpServletRequest`, `ResponseEntity`, `@RequestBody`, etc.)
- No direct SQL or JPQL queries — use repository interfaces
- No business logic that belongs inside a domain entity method
- No `@RestController` or other adapter-layer annotations
