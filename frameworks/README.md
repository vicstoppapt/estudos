# Frameworks na JVM (`java-frameworks-parent`)

**JDK 17** · **três submódulos Maven** com a **mesma API** `POST/GET /api/articles`, **camadas** (API → aplicação → domínio → infra) e **validação** na fronteira. Portas distintas para correr em paralelo: **8080** (Spring), **8081** (Micronaut), **8082** (Quarkus).

| Submódulo | Stack | Pacote raiz do código |
|-----------|--------|------------------------|
| `frameworks-springboot` | Spring Boot 3.3.x | `com.estudos.frameworks.springboot` |
| `frameworks-micronaut` | Micronaut 4 (platform 4.6.2) | `com.estudos.frameworks.micronaut` |
| `frameworks-quarkus` | Quarkus 3.17.x (REST + Jackson) | `com.estudos.frameworks.quarkus` |

## Estrutura de pacotes (cada módulo)

```text
…frameworks.<stack>
├── bootstrap/          # main / Micronaut.run (só onde faz sentido)
├── support/            # arranque (banner, listeners)
├── api/                # REST + DTOs + tratamento de erros HTTP
├── application/        # ArticleService (caso de uso)
├── domain/             # Article, exceções, repository/ (interface)
└── infrastructure/persistence/   # InMemoryArticleRepository
```

Spring Boot usa `@SpringBootApplication(scanBasePackages = "com.estudos.frameworks.springboot")` — sem classes “soltas” fora deste prefixo.

## Documentação (teoria)

Pasta [`docs/`](docs/): diagrama e fluxo em [`mvcerestcamadas.md`](docs/mvcerestcamadas.md) (referência alinhada ao módulo Spring); catálogo em [`padroescatalogo.md`](docs/padroescatalogo.md); ordem de leitura:

| # | Documento |
|---|-----------|
| 1 | [niveisdefinicao.md](docs/niveisdefinicao.md) |
| 2 | [oqueeumframework.md](docs/oqueeumframework.md) |
| 3 | [injecaodependencias.md](docs/injecaodependencias.md) |
| 4 | [mvcerestcamadas.md](docs/mvcerestcamadas.md) |
| 5 | [padroescatalogo.md](docs/padroescatalogo.md) |
| 6 | [springbootniveis.md](docs/springbootniveis.md) |
| 7 | [outrosframeworksjvm.md](docs/outrosframeworksjvm.md) |

## API de exemplo (`/api/articles`)

| Método | Caminho | Notas |
|--------|---------|--------|
| `POST` | `/api/articles` | `{"title":"...","body":"..."}` — `201` + JSON |
| `GET` | `/api/articles` | lista |
| `GET` | `/api/articles/{id}` | `404` se não existir |
| `POST` com `title` inválido | — | `400` + detalhe de validação (forma varia ligeiramente por stack) |

## Executar

Na raiz do agregador (`frameworks/`):

```bash
mvn -q -pl frameworks-springboot spring-boot:run
mvn -q -pl frameworks-micronaut mn:run
mvn -q -pl frameworks-quarkus quarkus:dev
```

URLs: `http://localhost:8080`, `8081`, `8082` respectivamente.

## Testes (todos os módulos)

```bash
cd frameworks
mvn -q test
```

- **Spring:** `ArticleApiTest` (`MockMvc` + `@DirtiesContext`), `DefaultArticleServiceTest` (Mockito).
- **Micronaut:** `ArticleApiTest` (`@MicronautTest` + `HttpClient`).
- **Quarkus:** `ArticleResourceIT` (RestAssured, `@Order` por estado em memória).

## Pré-requisitos no repositório

`basics`, `java11` (HTTP), `core` (OOP). Plano: [README principal](../README.md).
