# Spring Boot by level (basic → advanced)

**`frameworks` aggregator** (submodule `frameworks-springboot`). Level definitions: [niveisdefinicao-en.md](niveisdefinicao-en.md). **MVC + layers:** [mvcerestcamadas-en.md](mvcerestcamadas-en.md) · **Patterns:** [padroescatalogo-en.md](padroescatalogo-en.md). Micronaut/Quarkus mirror the same layout in `com.estudos.frameworks.micronaut` / `com.estudos.frameworks.quarkus`.

**PT:** [springbootniveis.md](springbootniveis.md)

---

## Package map (full model)

```text
com.estudos.frameworks.springboot
├── bootstrap/
│   └── FrameworksSpringBootApplication.java
├── support/
│   └── StartupBannerRunner.java
├── api/
│   ├── ArticleController.java
│   ├── RestExceptionHandler.java
│   └── dto/
├── application/
│   ├── ArticleService.java
│   └── DefaultArticleService.java
├── domain/
│   ├── Article.java
│   ├── ArticleNotFoundException.java
│   └── repository/
│       └── ArticleRepository.java
└── infrastructure/
    └── persistence/
        └── InMemoryArticleRepository.java
```

---

## Basic level

### Concepts

- **`@SpringBootApplication(scanBasePackages = "…springboot")`** — bootstrap + *component scan* only under this module prefix.
- **REST + JSON** — `@RestController`, Jackson serialization.
- **MVC flow:** request → *Controller* → *Service* → *Repository* (diagram in [mvcerestcamadas-en.md](mvcerestcamadas-en.md)).

### Reference code

| Piece | File |
|-------|------|
| App entry | [`FrameworksSpringBootApplication`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/bootstrap/FrameworksSpringBootApplication.java) |
| HTTP / Controller | [`ArticleController`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/ArticleController.java) |
| Input/output DTOs | [`CreateArticleRequest`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/dto/CreateArticleRequest.java), [`ArticleResponse`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/dto/ArticleResponse.java) |
| Domain | [`Article`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/Article.java) |
| In-memory persistence | [`InMemoryArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/infrastructure/persistence/InMemoryArticleRepository.java) |
| Startup banner | [`StartupBannerRunner`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/support/StartupBannerRunner.java) |

### Mental exercise

Step through `POST /api/articles`: *DispatcherServlet* → argument resolver (`@RequestBody` + `@Valid`) → *service* → *repository* → `201` + JSON response.

---

## Intermediate level

### Concepts

- **Bean Validation** at the boundary (`@Valid` + annotations on DTO).
- **`@RestControllerAdvice`** + **`ProblemDetail`** (RFC 9457) for uniform 400/404 — [`RestExceptionHandler`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/RestExceptionHandler.java).
- **API tests** with `MockMvc` and `@DirtiesContext` when global state (in-memory repository) would leak between tests — [`ArticleApiTest`](../frameworks-springboot/src/test/java/com/estudos/frameworks/springboot/api/ArticleApiTest.java).
- **DTO / domain separation** — evolve HTTP contract without dragging the DB.

### Next steps (outside this repo)

- **`@ConfigurationProperties`** + `application-{profile}.properties`.
- **Spring Data JPA** + `@Transactional`.
- **OpenAPI** (Springdoc) for `/api/articles`.

---

## Advanced level

### Concepts

- **Conditional auto-configuration**; replace `InMemoryArticleRepository` with `JpaRepository` without changing `ArticleService` (LSP + DI).
- **Actuator**, metrics, *tracing* (root README — future steps).
- **Spring Security** in front of this API.
- **Native image / AOT** — compare with Quarkus/Micronaut ([outrosframeworksjvm-en.md](outrosframeworksjvm-en.md)).

### Reading

[Spring Boot](https://docs.spring.io/spring-boot/documentation.html) · [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html).

Next: [outrosframeworksjvm-en.md](outrosframeworksjvm-en.md).
