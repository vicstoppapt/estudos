# Estudos Java

Repositório modular: **fundamentos (`core`)** e **recursos por versão** do JDK. Cada pasta é um projeto **Maven** independente.

| Módulo   | JDK no `pom.xml` | Conteúdo resumido |
|----------|------------------|-------------------|
| `basics` | 17               | **Pai Maven:** linguagem, memória, SO/rede, **segurança**, **armazenamento** — **antes** do `core`; ver `basics/README.md` |
| `core`   | 17               | Paradigmas, JVM/GC, entrevistas; **trilha numerada** em `core/README.md` (fácil → difícil) |
| `java8`  | 8                | Lambda, Stream, Optional, `java.time`, default methods |
| `java11` | 11               | `var`, `HttpClient`, `String`/`Files`, single-file `misc/Ola.java` |
| `java17` | 17               | Records, sealed, `instanceof`, text blocks, switch expressão |
| `java21` | 21               | Virtual threads, sequenced collections, record patterns, `when` |
| `frameworks` | 17           | **Pai Maven** com **Spring Boot 3**, **Micronaut 4**, **Quarkus 3** — mesma API em camadas; ver `frameworks/README.md` e `frameworks/docs/` |

Detalhes por módulo: **`README.md` dentro de cada pasta** (`core/README.md`, `java8/README.md`, …).

**Auxiliar:** `java11/sample.txt` — usado por `ChallengeReadResource.main` se você rodar a partir da pasta `java11`.

**Leitura por tema:** em `core` e em cada módulo `java8`…`java21`, há **`README.md`** por pasta de exemplos/desafios (**índice**) e, em geral, **um `.md` em minúsculas por classe** no mesmo diretório do `.java` (nuances/teoria); o código mantém comentários de fluxo enxutos.

---

## Plano de estudos (sequência + prioridade)

**Legenda:** **P0** — base indispensável · **P1** — forte impacto no trabalho / entrevistas · **P2** — aprofundamento e versões mais recentes do JDK.

A ordem abaixo respeita **dependências** (ex.: referências na memória antes de concorrência pesada; **HTTP em código** após a teoria em `basics-networking`).

### Fase A — `basics` (pai Maven `java-basics-parent`, pasta `basics/`)

| # | Pri | Submódulo / “módulo” | Pacote Java | Foco |
|---|-----|----------------------|-------------|------|
| A.1 | P0 | `basics-language` | `com.estudos.basics` | Sintaxe, objetos, `static`/`final`/interfaces, fluxo, arrays; docs transversais no pacote (incl. API além de REST) |
| A.2 | P0 | `basics-memory` | `com.estudos.basics.memory` | RAM, stack/heap, referências, cache CPU, ponteiros vs Java |
| A.3 | P1 | `basics-storage` | `com.estudos.basics.storage` | SQL vs NoSQL, ACID/CAP, JDBC, ficheiros, object storage |
| A.4 | P1 | `basics-networking` | `com.estudos.basics.network` | TCP/UDP, DNS, HTTP/REST, gRPC; **em seguida** praticar HTTP no módulo **`java11`** (`com.estudos.java11.examples`) |
| A.5 | P1 | `basics-security` | `com.estudos.basics.security` | AuthN/Z, criptografia (conceito), OWASP, engenharia social, SQLi, DDoS, defesa em camadas |
| A.6 | P1 | `basics-os-concurrency` | `com.estudos.basics.os` | Processador, cores físicos/lógicos, threads SO/Java, dimensionamento; [`processadorcoresethreads.md`](basics/basics-os-concurrency/src/main/java/com/estudos/basics/os/processadorcoresethreads.md); ponte para `core` / `java21` |

### Fase B — `core` (JDK 17), com intercalção de `java8`

| # | Pri | Módulo Maven | Pacotes principais | Foco |
|---|-----|--------------|-------------------|------|
| B.1 | P0 | `core` itens 1–10 | `com.estudos.core.imperative`, `.exceptions`, `.oop`, `.generics_collections` | Strings, pass-by-value, exceções, `equals`/`hashCode`, coleções, genéricos — **trilha numerada** em `core/README.md` |
| B.2 | P0 | `java8` | `com.estudos.java8.examples`, `com.estudos.java8.challenges` | Lambda, Stream, Optional — **antes** de `core` → `DeclarativeVsImperative` |
| B.3 | P0 | `core` itens 11–14 | `com.estudos.core.declarative`, `.jvm`, `.concurrency` | Streams vs imperativo, memória JVM, GC, threads |
| B.4 | P1 | `core` desafios | `com.estudos.core.challenges.*` | Anagrama, `equals`/`hashCode`, contador thread-safe — após os tópicos indicados em `core/README.md` |

### Fase C — JDK por versão (P2 incremental)

| # | Pri | Módulo | Pacotes típicos | Foco |
|---|-----|--------|-----------------|------|
| C.1 | P1 | `java11` | `com.estudos.java11.examples`, `.challenges`, `.misc` | `var`, `HttpClient`, `Files`, single-source |
| C.2 | P2 | `java17` | `com.estudos.java17.examples`, `.challenges` | Records, sealed, pattern matching, text blocks |
| C.3 | P2 | `java21` | `com.estudos.java21.examples`, `.challenges` | Virtual threads, sequenced collections, record patterns |

### Fase D — Frameworks na JVM (`frameworks`)

| # | Pri | Módulo Maven | Pacote | Foco |
|---|-----|--------------|--------|------|
| D.1 | P1 | `frameworks` (`java-frameworks-parent`) | `com.estudos.frameworks.springboot` / `.micronaut` / `.quarkus` (+ `api`, `application`, `domain`, `infrastructure`) | **REST**, camadas, DTO, repositório, DI; Micronaut e Quarkus em paralelo — [`mvcerestcamadas.md`](frameworks/docs/mvcerestcamadas.md) |

**Quando:** após **linguagem** sólida (`basics-language`) e idealmente **`java11`** (HTTP); pode correr em paralelo a `java17`/`java21` conforme necessidade de emprego.

**Releitura:** cruza `basics-memory` e `basics-os-concurrency` com `core` → `JvmMemoryModelIntro` e `MultithreadingIntro` quando sentires que a teoria “cola” melhor com código.

---

## Comandos Maven

| Objetivo | Comando (dentro da pasta do módulo) |
|----------|-------------------------------------|
| Compilar | `mvn -q compile` |
| Testes   | `mvn test` |
| Rodar um exemplo | `mvn -q exec:java "-Dexec.mainClass=com.estudos.PACOTE.Classe"` |
| Spring Boot (`frameworks-springboot`) | `cd frameworks` · `mvn -q -pl frameworks-springboot spring-boot:run` (porta 8080) |
| Micronaut | `cd frameworks` · `mvn -q -pl frameworks-micronaut mn:run` (porta 8081) |
| Quarkus | `cd frameworks` · `mvn -q -pl frameworks-quarkus quarkus:dev` (porta 8082) |

**PowerShell:** o `-Dexec.mainClass=...` deve ir entre aspas (o ponto quebra o parser se solto).

**JDK:** use a versão do módulo (o **java21** exige JDK 21). O **java8** usa **Mockito 4.11** no `pom.xml` por compatibilidade com Java 8; os outros usam Mockito 5. O agregador **`frameworks`** compila com **Java 17** nos três submódulos (Spring Boot 3, Micronaut 4, Quarkus 3).

---

## Testes automatizados

Stack em todos os módulos com testes:

- **JUnit 5** (Jupiter) — execução e ciclo de vida
- **AssertJ** — asserções fluentes (`assertThat(...).isEqualTo(...)`)
- **Mockito** — mocks (`@Mock`, `when`, `verify`); extensão `mockito-junit-jupiter`

### Onde estão os testes (`src/test/java`)

| Módulo | Classes de teste (resumo) |
|--------|---------------------------|
| `core` | `ChallengeEqualsHashCodeTest`, `ChallengeThreadSafeCounterTest`, `ChallengeAnagramTest`, `RelogioMockitoDemoTest` |
| `java8` | `ChallengeStreamWordCountTest`, `ChallengeCollectorsGroupingTest`, `PredicateMockitoDemoTest` |
| `java11` | `ChallengeHttpHeadTest` (mock `HttpClient`), `ChallengeReadResourceTest` (`@TempDir`) |
| `java17` | `ChallengeSealedSwitchTest`, `ChallengeRecordValidationTest` |
| `java21` | `ChallengeSequencedPipelineTest`, `ChallengeVirtualHttpTest` (mock + executor síncrono no teste) |
| `frameworks` | `frameworks-springboot`: `ArticleApiTest`, `DefaultArticleServiceTest` · `frameworks-micronaut`: `ArticleApiTest` · `frameworks-quarkus`: `ArticleResourceIT` |

Versões: `frameworks-springboot` herda *spring-boot-starter-parent*; Micronaut usa `io.micronaut.platform:micronaut-parent` (`relativePath` vazio); Quarkus importa `quarkus-bom` no submódulo.

---

## Desafios (`challenges`) vs exemplos

- **Desafios:** há **solução de referência** no código-fonte principal para `mvn test` ficar verde. O Javadoc de cada desafio indica como “reverter” ou variar para estudar (ex.: em `core`, comentar `hashCode` em `ChallengeEqualsHashCode`).
- **Exemplos:** código didático com `main`. Em **todos** os módulos, cada exemplo inclui **`meuPlayground()`** e **`// meuPlayground();`** no `main` — espaço livre para experimentar; **não** é o mesmo que os desafios oficiais.

---

## Conceitos só no `core`

- **`package-info.java`:** documentação do pacote (JavaDoc), não é classe executável — explicado em `core/README.md`.
- **Paradigmas** (`imperative`, `oop`, `declarative`, …): ver tabela em `core/README.md`.

---

## Próximos passos (fora do `basics` neste repositório)

Temas para um módulo ou documentação futura — ligados a **DevOps**, **pipelines** e **automação**; **não** estão expandidos no `basics` neste momento:

- **Observabilidade:** métricas (ex.: Prometheus), *tracing* distribuído (OpenTelemetry), logs estruturados, SLI/SLO, alertas e *dashboards*.
- **CI/CD:** *pipelines* (GitHub Actions, GitLab CI, Jenkins), artefactos, promoção entre ambientes, *rollback*.
- **Automação / infra como código:** Terraform, Ansible, Helm; políticas, *secrets* no pipeline, *governance*.

---

## Leitura oficial

- [JLS](https://docs.oracle.com/javase/specs/jls/se17/html/) · [JVMS](https://docs.oracle.com/javase/specs/jvms/se17/html/)
