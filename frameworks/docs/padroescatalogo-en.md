# Catalog of implemented or referenced patterns (`frameworks`)

Quick map of what the code and docs cover. Not everything has a dedicated class — some are natural **next steps**.

**PT:** [padroescatalogo.md](padroescatalogo.md)

---

## Implemented in the `Article` example

| Pattern | Where | Notes |
|---------|-------|--------|
| **MVC** (REST) | `api.ArticleController` + JSON DTOs | *View* = JSON representation |
| **Layers** (*layered*) | `api` → `application` → `domain` → `infrastructure` | Dependency toward domain |
| **Repository** | `ArticleRepository` + `InMemoryArticleRepository` | Abstracts persistence |
| **Application / Use case service** | `ArticleService`, `DefaultArticleService` | Orchestrates repository and local rules |
| **DTO** (*Data Transfer Object*) | `CreateArticleRequest`, `ArticleResponse` | Stable HTTP contract |
| **Dependency injection** | Constructors + `@Service`, `@Repository`, `@RestController` (Spring); CDI/`@Singleton` equivalents in other modules | See [injecaodependencias-en.md](injecaodependencias-en.md) |
| **Centralized errors** | `RestExceptionHandler` + `ProblemDetail` | Standard 404 / 400 |
| **Bean Validation** | `@Valid`, `@NotBlank`, `@Size` on DTO | Validation at HTTP boundary |

---

## Related (common in Spring projects — study later)

| Pattern | Idea | Where it usually appears |
|---------|------|---------------------------|
| **Front Controller** | One servlet dispatches all | `DispatcherServlet` (Spring MVC) |
| **Template Method** | Fixed flow with *hooks* | `JdbcTemplate`, `RestTemplate` / `RestClient` |
| **Adapter** | Fit external interface to yours | HTTP clients, payment gateways |
| **Strategy** | Swap algorithm at runtime | `PasswordEncoder`, *cache* policies |
| **Factory** | Encapsulated creation | `BeanFactory`, API client builders |
| **Decorator** | Stack behavior | Servlet *filters*, `ClientHttpRequestInterceptor` |
| **CQRS** | Separate read and write | Distinct services/queries in large systems |
| **Hexagonal / Ports & Adapters** | Explicit ports (in/out) | Similar to repository as *port*; `port.in` / `port.out` in larger projects |

---

## Antipatterns to avoid

- **Fat controller:** SQL and business rules inside `@RestController`.
- Excessive **anemic domain model**: only DTOs and procedural services with no behavior in the domain (OK for simple CRUD; for rich rules, move logic to entities/value objects).
- **DTO = JPA entity** exposed in JSON — couples public contract to DB schema.

---

## Suggested reading

- [mvcerestcamadas-en.md](mvcerestcamadas-en.md) — flow diagram and packages.
- [springbootniveis-en.md](springbootniveis-en.md) — basic → advanced on this model.
