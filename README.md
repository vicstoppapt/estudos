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

Detalhes por módulo: **`README.md` dentro de cada pasta** (`core/README.md`, `java8/README.md`, …).

**Auxiliar:** `java11/sample.txt` — usado por `ChallengeReadResource.main` se você rodar a partir da pasta `java11`.

**Leitura por tema:** em `core` e em cada módulo `java8`…`java21`, há **`README.md`** por pasta de exemplos/desafios (**índice**) e, em geral, **um `.md` em minúsculas por classe** no mesmo diretório do `.java` (nuances/teoria); o código mantém comentários de fluxo enxutos.

---

## Comandos Maven

| Objetivo | Comando (dentro da pasta do módulo) |
|----------|-------------------------------------|
| Compilar | `mvn -q compile` |
| Testes   | `mvn test` |
| Rodar um exemplo | `mvn -q exec:java "-Dexec.mainClass=com.estudos.PACOTE.Classe"` |

**PowerShell:** o `-Dexec.mainClass=...` deve ir entre aspas (o ponto quebra o parser se solto).

**JDK:** use a versão do módulo (o **java21** exige JDK 21). O **java8** usa **Mockito 4.11** no `pom.xml` por compatibilidade com Java 8; os outros usam Mockito 5.

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

Versões exatas das bibliotecas: propriedades no `pom.xml` de cada módulo.

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
