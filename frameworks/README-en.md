**PT:** [README.md](README.md)

# JVM frameworks (`java-frameworks-parent`)

**Repo `estudos`:** global commands and glossary — [DOCUMENTATION.md](../DOCUMENTATION.md) · [GLOSSARY.md](../GLOSSARY.md) (REST, DI, DTO terms in English).

**JDK 17** · **three Maven submodules** with the **same** `POST/GET /api/articles` API, **layers** (API → application → domain → infra), and **validation** at the boundary. Distinct ports to run in parallel: **8080** (Spring), **8081** (Micronaut), **8082** (Quarkus).

| Submodule | Stack | Root code package |
|-----------|--------|------------------------|
| `frameworks-springboot` | Spring Boot 3.3.x | `com.estudos.frameworks.springboot` |
| `frameworks-micronaut` | Micronaut 4 (platform 4.6.2) | `com.estudos.frameworks.micronaut` |
| `frameworks-quarkus` | Quarkus 3.17.x (REST + Jackson) | `com.estudos.frameworks.quarkus` |

## Package layout (each module)

```text
…frameworks.<stack>
├── bootstrap/          # main / Micronaut.run (where it makes sense)
├── support/            # startup (banner, listeners)
├── api/                # REST + DTOs + HTTP error handling
├── application/        # ArticleService (use case)
├── domain/             # Article, exceptions, repository/ (interface)
└── infrastructure/persistence/   # InMemoryArticleRepository
```

Spring Boot uses `@SpringBootApplication(scanBasePackages = "com.estudos.frameworks.springboot")` — no stray classes outside this prefix.

## Documentation (theory)

[`docs/`](docs/) folder: diagram and flow in [`mvcerestcamadas.md`](docs/mvcerestcamadas.md) (reference aligned with the Spring module); catalog in [`padroescatalogo.md`](docs/padroescatalogo.md); reading order:

| # | Document |
|---|-----------|
| 1 | [niveisdefinicao.md](docs/niveisdefinicao.md) |
| 2 | [oqueeumframework.md](docs/oqueeumframework.md) |
| 3 | [injecaodependencias.md](docs/injecaodependencias.md) |
| 4 | [mvcerestcamadas.md](docs/mvcerestcamadas.md) |
| 5 | [padroescatalogo.md](docs/padroescatalogo.md) |
| 6 | [springbootniveis.md](docs/springbootniveis.md) |
| 7 | [outrosframeworksjvm.md](docs/outrosframeworksjvm.md) |

## Sample API (`/api/articles`)

| Method | Path | Notes |
|--------|---------|--------|
| `POST` | `/api/articles` | `{"title":"...","body":"..."}` — `201` + JSON |
| `GET` | `/api/articles` | list |
| `GET` | `/api/articles/{id}` | `404` if missing |
| `POST` with invalid `title` | — | `400` + validation detail (shape varies slightly by stack) |

## Run

At the aggregator root (`frameworks/`):

```bash
mvn -q -pl frameworks-springboot spring-boot:run
mvn -q -pl frameworks-micronaut mn:run
mvn -q -pl frameworks-quarkus quarkus:dev
```

URLs: `http://localhost:8080`, `8081`, `8082` respectively.

## Tests (all modules)

```bash
cd frameworks
mvn -q test
```

- **Spring:** `ArticleApiTest` (`MockMvc` + `@DirtiesContext`), `DefaultArticleServiceTest` (Mockito).
- **Micronaut:** `ArticleApiTest` (`@MicronautTest` + `HttpClient`).
- **Quarkus:** `ArticleResourceIT` (RestAssured, `@Order` for in-memory state).

## Prerequisites in the repository

`basics`, `java11` (HTTP), `core` (OOP). Plan: [main README-en.md](../README-en.md).
