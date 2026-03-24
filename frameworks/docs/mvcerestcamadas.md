# MVC em REST e arquitetura em camadas (modelo deste módulo)

**English:** [mvcerestcamadas-en.md](mvcerestcamadas-en.md)

Este projeto usa **Spring MVC** com **API REST** (JSON). O “**V**” (*View*) não são ficheiros JSP/Thymeleaf, mas a **representação** devolvida ao cliente (JSON serializado a partir de **DTOs**).

---

## 1. Fluxo de um pedido `POST /api/articles`

```text
HTTP JSON  -->  [Controller]  -->  [Application Service]  -->  [Repository impl]
   |                    |                    |                         |
CreateArticleRequest   valida @Valid    regras / orquestração    persiste Article
   ^                    |                    |                         |
   |              ArticleResponse <-------- Article (domínio) <-------+
   |
Jackson (JSON)
```

1. **Tomcat** recebe o pedido; **DispatcherServlet** encaminha ao método mapeado (`@PostMapping`).
2. O corpo é desserializado para **`CreateArticleRequest`** (DTO de entrada).
3. **`@Valid`** dispara Bean Validation; erros vão ao **`RestExceptionHandler`** (HTTP 400).
4. O **Controller** chama **`ArticleService`** — não toca no repositório.
5. O **Service** cria/agrega **`Article`** e usa **`ArticleRepository`**.
6. **`InMemoryArticleRepository`** (infra) guarda em memória (em produção seria JPA/JDBC).
7. A resposta mapeia **`Article`** → **`ArticleResponse`** (DTO de saída).

---

## 2. Papéis no padrão MVC (adaptado a REST)

| Papel | Neste módulo | Responsabilidade |
|-------|----------------|------------------|
| **Model** | `domain.Article`, exceções de domínio | Estado e invariantes do negócio (sem HTTP) |
| **View** | (implícito) JSON de `ArticleResponse` | Formato apresentado ao cliente |
| **Controller** | `api.ArticleController` | HTTP, códigos de estado, mapeamento DTO ↔ chamadas ao serviço |

---

## 3. Camadas (layered architecture)

| Camada | Pacote | Depende de |
|--------|--------|------------|
| **API** | `com.estudos.frameworks.springboot.api` | `application`, `api.dto` |
| **Aplicação** | `com.estudos.frameworks.springboot.application` | `domain` (+ interface do repositório) |
| **Domínio** | `com.estudos.frameworks.springboot.domain` | nada de Spring / HTTP |
| **Infraestrutura** | `com.estudos.frameworks.springboot.infrastructure.persistence` | `domain`, Spring `@Repository` |

**Regra:** dependências apontam **para dentro** (domínio no centro). A infra implementa interfaces definidas junto do domínio (`ArticleRepository`).

---

## 4. Onde está o código

| Ficheiro / tipo | Camada |
|-----------------|--------|
| [`ArticleController`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/ArticleController.java) | API / Controller |
| [`CreateArticleRequest`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/dto/CreateArticleRequest.java), [`ArticleResponse`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/dto/ArticleResponse.java) | DTO |
| [`RestExceptionHandler`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/RestExceptionHandler.java) | Tratamento transversal de erros |
| [`ArticleService`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/application/ArticleService.java), [`DefaultArticleService`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/application/DefaultArticleService.java) | Caso de uso |
| [`Article`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/Article.java), [`ArticleNotFoundException`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/ArticleNotFoundException.java) | Modelo / erros |
| [`ArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/repository/ArticleRepository.java) | Contrato de persistência |
| [`InMemoryArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/infrastructure/persistence/InMemoryArticleRepository.java) | Implementação |

---

## 5. Extensões típicas (não estão todas no código)

- **JPA:** entidade `@Entity`, `JpaArticleRepository` na infra, `spring-boot-starter-data-jpa`.
- **MapStruct / manual:** mapear `Article` ↔ DTO quando o domínio divergir do contrato público.
- **OpenAPI (Springdoc):** documentar `POST/GET` para equipas cliente.
- **Segurança:** Spring Security em cima desta API (`basics-security`).

Catálogo de padrões nomeados: [padroescatalogo.md](padroescatalogo.md).
