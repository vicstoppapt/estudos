# MVC in REST and layered architecture (this module’s model)

This project uses **Spring MVC** with a **REST API** (JSON). The “**V**” (*View*) is not JSP/Thymeleaf files but the **representation** returned to the client (JSON serialized from **DTOs**).

**PT:** [mvcerestcamadas.md](mvcerestcamadas.md)

---

## 1. Flow of a `POST /api/articles` request

```text
HTTP JSON  -->  [Controller]  -->  [Application Service]  -->  [Repository impl]
   |                    |                    |                         |
CreateArticleRequest   @Valid validation   rules / orchestration   persists Article
   ^                    |                    |                         |
   |              ArticleResponse <-------- Article (domain) <-------+
   |
Jackson (JSON)
```

1. **Tomcat** receives the request; **DispatcherServlet** dispatches to the mapped method (`@PostMapping`).
2. The body is deserialized to **`CreateArticleRequest`** (input DTO).
3. **`@Valid`** triggers Bean Validation; errors go to **`RestExceptionHandler`** (HTTP 400).
4. The **Controller** calls **`ArticleService`** — it does not touch the repository.
5. The **Service** builds **`Article`** and uses **`ArticleRepository`**.
6. **`InMemoryArticleRepository`** (infra) stores in memory (in production: JPA/JDBC).
7. The response maps **`Article`** → **`ArticleResponse`** (output DTO).

---

## 2. MVC roles (REST-adapted)

| Role | In this module | Responsibility |
|------|----------------|----------------|
| **Model** | `domain.Article`, domain exceptions | Business state and invariants (no HTTP) |
| **View** | (implicit) JSON from `ArticleResponse` | Format shown to the client |
| **Controller** | `api.ArticleController` | HTTP, status codes, DTO ↔ service calls |

---

## 3. Layers

| Layer | Package | Depends on |
|-------|---------|------------|
| **API** | `com.estudos.frameworks.springboot.api` | `application`, `api.dto` |
| **Application** | `com.estudos.frameworks.springboot.application` | `domain` (+ repository interface) |
| **Domain** | `com.estudos.frameworks.springboot.domain` | no Spring / HTTP |
| **Infrastructure** | `com.estudos.frameworks.springboot.infrastructure.persistence` | `domain`, Spring `@Repository` |

**Rule:** dependencies point **inward** (domain at the center). Infra implements interfaces next to the domain (`ArticleRepository`).

---

## 4. Where the code lives

| File / type | Layer |
|-------------|--------|
| [`ArticleController`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/ArticleController.java) | API / Controller |
| [`CreateArticleRequest`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/dto/CreateArticleRequest.java), [`ArticleResponse`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/dto/ArticleResponse.java) | DTO |
| [`RestExceptionHandler`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/RestExceptionHandler.java) | Cross-cutting errors |
| [`ArticleService`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/application/ArticleService.java), [`DefaultArticleService`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/application/DefaultArticleService.java) | Use case |
| [`Article`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/Article.java), [`ArticleNotFoundException`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/ArticleNotFoundException.java) | Model / errors |
| [`ArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/repository/ArticleRepository.java) | Persistence contract |
| [`InMemoryArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/infrastructure/persistence/InMemoryArticleRepository.java) | Implementation |

---

## 5. Typical extensions (not all in code)

- **JPA:** `@Entity`, `JpaArticleRepository` in infra, `spring-boot-starter-data-jpa`.
- **MapStruct / manual:** map `Article` ↔ DTO when domain diverges from public contract.
- **OpenAPI (Springdoc):** document `POST/GET` for client teams.
- **Security:** Spring Security in front of this API (`basics-security`).

Pattern catalog: [padroescatalogo-en.md](padroescatalogo-en.md).
