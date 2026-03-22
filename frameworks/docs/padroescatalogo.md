# Catálogo de padrões implementados ou referenciados (`frameworks`)

Mapa rápido do que o código e a documentação cobrem. Nem tudo tem classe dedicada — alguns são **próximos passos** naturais.

---

## Implementados no exemplo `Article`

| Padrão | Onde | Notas |
|--------|------|--------|
| **MVC** (REST) | `api.ArticleController` + DTOs JSON | *View* = representação JSON |
| **Camadas** (*layered*) | `api` → `application` → `domain` → `infrastructure` | Dependência para o domínio |
| **Repository** | `ArticleRepository` + `InMemoryArticleRepository` | Abstrai persistência |
| **Application / Use case service** | `ArticleService`, `DefaultArticleService` | Orquestra repositório e regras locais |
| **DTO** (*Data Transfer Object*) | `CreateArticleRequest`, `ArticleResponse` | Contrato HTTP estável |
| **Injeção de dependências** | Construtores + `@Service`, `@Repository`, `@RestController` (Spring); equivalentes CDI/`@Singleton` nos outros módulos | Ver [injecaodependencias.md](injecaodependencias.md) |
| **Centralização de erros** | `RestExceptionHandler` + `ProblemDetail` | 404 / 400 padronizados |
| **Bean Validation** | `@Valid`, `@NotBlank`, `@Size` no DTO | Validação na fronteira HTTP |

---

## Relacionados (comuns em projetos Spring — estudo posterior)

| Padrão | Ideia | Onde costuma aparecer |
|--------|--------|------------------------|
| **Front Controller** | Um servlet despacha tudo | `DispatcherServlet` (Spring MVC) |
| **Template Method** | Fluxo fixo com *hooks* | `JdbcTemplate`, `RestTemplate` / `RestClient` |
| **Adapter** | Encaixar interface externa na tua | Clientes HTTP, gateways de pagamento |
| **Strategy** | Troca algoritmo em runtime | `PasswordEncoder`, políticas de *cache* |
| **Factory** | Criação encapsulada | `BeanFactory`, builders de cliente API |
| **Decorator** | Empilhar comportamento | *Filters* servlet, `ClientHttpRequestInterceptor` |
| **CQRS** | Separar leitura de escrita | Serviços/queries distintos em sistemas grandes |
| **Hexagonal / Ports & Adapters** | Portas explícitas (in/out) | Semelhante ao repositório como *port*; pastas `port.in` / `port.out` em projetos maiores |

---

## Antipadrões a evitar

- **Controller gordo:** SQL e regra de negócio no `@RestController`.
- **Anemic domain model** excessivo: só DTOs e serviços procedurais sem comportamento no domínio (aceitável em CRUD simples; em regras ricas, move lógica para entidades/value objects).
- **DTO = entidade JPA** exposto no JSON — acopla contrato público ao esquema da BD.

---

## Leitura sugerida

- [mvcerestcamadas.md](mvcerestcamadas.md) — diagrama de fluxo e pacotes.
- [springbootniveis.md](springbootniveis.md) — evolução básico → avançado sobre este modelo.
