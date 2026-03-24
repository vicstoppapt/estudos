# Basic, intermediate, and advanced levels (this module)

Criteria used **in this repository** for **frameworks and Spring Boot**. Aligns expectations: the same label means different things per team; here it is **explicit**.

**PT:** [niveisdefinicao.md](niveisdefinicao.md)

---

## **Basic** level

You can:

- Explain the difference between **library** and **framework** (who calls whom).
- Start a **Spring Boot** app, follow **HTTP → Controller → Service → Repository**, and read JSON **DTOs**.
- Explain **dependency injection** in words and prefer **constructor** injection.
- Navigate the **package map** in [springbootniveis-en.md](springbootniveis-en.md).

**Not required yet:** JPA, distributed transactions, full Security, OpenAPI.

---

## **Intermediate** level

You can:

- Justify **DTO** vs **domain** model; use **`@Valid`** and interpret **`ProblemDetail`** errors.
- Explain **`@RestControllerAdvice`** and map exceptions to HTTP.
- Test the API with **`MockMvc`** and know when **shared state** (e.g. in-memory repository) needs **`@DirtiesContext`** or another strategy.
- Extend with **`@ConfigurationProperties`** or **profiles** (exercise outside minimal code).

---

## **Advanced** level

You can:

- Swap **`InMemoryArticleRepository`** for **JPA** while keeping the repository contract and **service** stable.
- Read Spring Boot **auto-configuration** and **conditionals** (`@ConditionalOn*`).
- Integrate **Security**, **observability** (Actuator, metrics) and compare **native image** with **Quarkus/Micronaut**.

---

## Where this shows up in code and text

| Level | Where to study in this module |
|-------|------------------------------|
| Basic | [mvcerestcamadas-en.md](mvcerestcamadas-en.md), [padroescatalogo-en.md](padroescatalogo-en.md), packages `springboot.api`, `springboot.application`, `springboot.domain`, `springboot.infrastructure` (mirrored in other stacks) |
| Intermediate | [`RestExceptionHandler`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/RestExceptionHandler.java), [`ArticleApiTest`](../frameworks-springboot/src/test/java/com/estudos/frameworks/springboot/api/ArticleApiTest.java) |
| Advanced | [springbootniveis-en.md](springbootniveis-en.md) § advanced, [outrosframeworksjvm-en.md](outrosframeworksjvm-en.md) |

Start with [oqueeumframework-en.md](oqueeumframework-en.md) and [injecaodependencias-en.md](injecaodependencias-en.md).
