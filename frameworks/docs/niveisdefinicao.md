# Níveis básico, intermédio e avançado (este módulo)

**English:** [niveisdefinicao-en.md](niveisdefinicao-en.md)

Critérios usados **neste repositório** para o tópico **frameworks e Spring Boot**. Serve para alinhar expectativas: o mesmo termo muda consoante a equipa; aqui fica **explícito**.

---

## Nível **básico**

Consegues:

- Dizer a diferença entre **biblioteca** e **framework** (quem chama quem).
- Arrancar a app **Spring Boot**, seguir o fluxo **HTTP → Controller → Service → Repository** e ler o JSON de **DTOs**.
- Explicar **injeção de dependências** em palavras e reconhecer **construtor** como estilo preferido.
- Navegar o **mapa de pacotes** em [springbootniveis.md](springbootniveis.md).

**Não é obrigatório ainda:** JPA, transações distribuídas, Security completo, OpenAPI.

---

## Nível **intermédio**

Consegues:

- Justificar **DTO** vs modelo de **domínio**; usar **`@Valid`** e interpretar erros **`ProblemDetail`**.
- Explicar o papel de **`@RestControllerAdvice`** e mapear exceções a HTTP.
- Testar a API com **`MockMvc`** e perceber quando **estado partilhado** (ex. repositório em memória) exige **`@DirtiesContext`** ou outra estratégia.
- Estender o modelo com **`@ConfigurationProperties`** ou **profiles** (exercício fora do código mínimo).

---

## Nível **avançado**

Consegues:

- Trocar **`InMemoryArticleRepository`** por **JPA** mantendo o contrato do repositório e o **service** estável.
- Ler **auto-configuração** Spring Boot e **condicionais** (`@ConditionalOn*`).
- Integrar **Security**, **observabilidade** (Actuator, métricas) e comparar **native image** com **Quarkus/Micronaut**.

---

## Onde isto aparece no código e texto

| Nível | Onde estudar neste módulo |
|-------|---------------------------|
| Básico | [mvcerestcamadas.md](mvcerestcamadas.md), [padroescatalogo.md](padroescatalogo.md), pacotes `springboot.api`, `springboot.application`, `springboot.domain`, `springboot.infrastructure` (e espelho nos outros stacks) |
| Intermédio | [`RestExceptionHandler`](../frameworks-springboot/src/main/java/com/estudos/frameworks/springboot/api/RestExceptionHandler.java), [`ArticleApiTest`](../frameworks-springboot/src/test/java/com/estudos/frameworks/springboot/api/ArticleApiTest.java) |
| Avançado | [springbootniveis.md](springbootniveis.md) § avançado, [outrosframeworksjvm.md](outrosframeworksjvm.md) |

Começa por [oqueeumframework.md](oqueeumframework.md) e [injecaodependencias.md](injecaodependencias.md).
