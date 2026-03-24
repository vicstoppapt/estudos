# Spring Boot por níveis (básico → avançado)

**English:** [springbootniveis-en.md](springbootniveis-en.md)

**Agregador `frameworks`** (submódulo `frameworks-springboot`). Definição dos níveis: [niveisdefinicao.md](niveisdefinicao.md). **Arquitetura MVC + camadas:** [mvcerestcamadas.md](mvcerestcamadas.md) · **Padrões:** [padroescatalogo.md](padroescatalogo.md). Micronaut/Quarkus repetem o mesmo recorte em `com.estudos.frameworks.micronaut` / `com.estudos.frameworks.quarkus`.

---

## Mapa de pacotes (modelo completo)

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

## Nível básico

### Conceitos

- **`@SpringBootApplication(scanBasePackages = "…springboot")`** — arranque + *component scan* só no prefixo do módulo.
- **REST + JSON** — `@RestController`, serialização com Jackson.
- **Fluxo MVC:** pedido → *Controller* → *Service* → *Repository* (ver diagrama em [mvcerestcamadas.md](mvcerestcamadas.md)).

### Código de referência

| Peça | Ficheiro |
|------|----------|
| Entrada da app | [`FrameworksSpringBootApplication`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/bootstrap/FrameworksSpringBootApplication.java) |
| HTTP / Controller | [`ArticleController`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/ArticleController.java) |
| DTO entrada/saída | [`CreateArticleRequest`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/dto/CreateArticleRequest.java), [`ArticleResponse`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/dto/ArticleResponse.java) |
| Domínio | [`Article`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/domain/Article.java) |
| Persistência em memória | [`InMemoryArticleRepository`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/infrastructure/persistence/InMemoryArticleRepository.java) |
| Arranque | [`StartupBannerRunner`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/support/StartupBannerRunner.java) |

### Exercício mental

Depura o `POST /api/articles`: *DispatcherServlet* → argument resolver (`@RequestBody` + `@Valid`) → *service* → *repository* → resposta `201` + JSON.

---

## Nível intermédio

### Conceitos

- **Bean Validation** na fronteira (`@Valid` + anotações no DTO).
- **`@RestControllerAdvice`** + **`ProblemDetail`** (RFC 9457) para 400/404 uniformes — [`RestExceptionHandler`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/RestExceptionHandler.java).
- **Testes de API** com `MockMvc` e `@DirtiesContext` quando o estado global (repositório em memória) entre testes interferiria — [`ArticleApiTest`](../frameworks-springboot/src/test/java/com/estudos/frameworks/springboot/api/ArticleApiTest.java).
- **Separação DTO / domínio** — evoluir contrato HTTP sem arrastar a BD.

### Próximos passos (fora deste repo)

- **`@ConfigurationProperties`** + `application-{profile}.properties`.
- **Spring Data JPA** + `@Transactional`.
- **OpenAPI** (Springdoc) para documentar `/api/articles`.

---

## Nível avançado

### Conceitos

- **Auto-configuração** condicional; substituir `InMemoryArticleRepository` por `JpaRepository` sem mudar `ArticleService` (LSP + DI).
- **Actuator**, métricas, *tracing* (README raiz — próximos passos).
- **Spring Security** na frente desta API.
- **Native image / AOT** — comparar com Quarkus/Micronaut ([outrosframeworksjvm.md](outrosframeworksjvm.md)).

### Leitura

[Spring Boot](https://docs.spring.io/spring-boot/documentation.html) · [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html).

Seguinte: [outrosframeworksjvm.md](outrosframeworksjvm.md).
