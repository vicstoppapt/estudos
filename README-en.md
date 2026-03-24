**PT:** [README.md](README.md)

# Java studies

**Running all modules (Maven commands, JDK per folder):** [DOCUMENTATION-en.md](DOCUMENTATION-en.md) · **PT↔EN glossary (whole repo):** [GLOSSARY-en.md](GLOSSARY-en.md)

Modular repository: **foundations (`core`)** and **JDK version features**. Each folder is an independent **Maven** project.

| Module   | JDK in `pom.xml` | Summary |
|----------|------------------|---------|
| `basics` | 17               | **Maven parent:** language, memory, OS/network, **security**, **storage** — **before** `core`; see [`basics/README-en.md`](basics/README-en.md) |
| `core`   | 17               | Paradigms, JVM/GC, interviews; **numbered track** in [`core/README-en.md`](core/README-en.md) (easy → hard) |
| `java8`  | 8                | Lambda, Stream, Optional, `java.time`, default methods — [`java8/README-en.md`](java8/README-en.md) |
| `java11` | 11               | `var`, `HttpClient`, `String`/`Files`, single-file `misc/Ola.java` — [`java11/README-en.md`](java11/README-en.md) |
| `java17` | 17               | Records, sealed, `instanceof`, text blocks, `switch` expression — [`java17/README-en.md`](java17/README-en.md) |
| `java21` | 21               | Virtual threads, sequenced collections, record patterns, `when` — [`java21/README-en.md`](java21/README-en.md) |
| `frameworks` | 17           | **Maven parent** with **Spring Boot 3**, **Micronaut 4**, **Quarkus 3** — same layered API; see [`frameworks/README-en.md`](frameworks/README-en.md) and `frameworks/docs/` |

Per-module details: **`README-en.md` / `README.md` inside each folder** ([`core/README-en.md`](core/README-en.md), [`java8/README-en.md`](java8/README-en.md), …).

**Helper:** `java11/sample.txt` — used by `ChallengeReadResource.main` if you run from the `java11` folder.

**Reading by topic:** in `core` and each `java8`…`java21` module, there is a **`README-en.md`** / **`README.md`** per examples/challenges folder (**index**), and usually **one lowercase `*-en.md` / `.md` per class** next to the `.java` (nuances/theory); the code keeps short flow comments.

---

## Study plan (sequence + priority)

**Legend:** **P0** — essential base · **P1** — strong impact on work / interviews · **P2** — depth and newer JDK versions.

The order below respects **dependencies** (e.g. memory references before heavy concurrency; **HTTP in code** after theory in `basics-networking`).

### Phase A — `basics` (Maven parent `java-basics-parent`, `basics/` folder)

| # | Pri | Submodule / “module” | Java package | Focus |
|---|-----|----------------------|-------------|------|
| A.1 | P0 | `basics-language` | `com.estudos.basics` | Syntax, objects, `static`/`final`/interfaces, flow, arrays; cross-cutting docs in the package (incl. API beyond REST) |
| A.2 | P0 | `basics-memory` | `com.estudos.basics.memory` | RAM, stack/heap, references, CPU cache, pointers vs Java |
| A.3 | P1 | `basics-storage` | `com.estudos.basics.storage` | SQL vs NoSQL, ACID/CAP, JDBC, files, object storage |
| A.4 | P1 | `basics-networking` | `com.estudos.basics.network` | TCP/UDP, DNS, HTTP/REST, gRPC; **then** practice HTTP in **`java11`** (`com.estudos.java11.examples`) |
| A.5 | P1 | `basics-security` | `com.estudos.basics.security` | AuthN/Z, cryptography (concepts), OWASP, social engineering, SQLi, DDoS, defense in depth |
| A.6 | P1 | `basics-os-concurrency` | `com.estudos.basics.os` | PT/EN + glossary: [`README-en.md`](basics/basics-os-concurrency/README-en.md), [`glossary-os-concurrency-en.md`](basics/basics-os-concurrency/src/main/java/com/estudos/basics/os/glossary-os-concurrency-en.md); bridge to `core` / `java21` |

### Phase B — `core` (JDK 17), interleaving `java8`

| # | Pri | Maven module | Main packages | Focus |
|---|-----|--------------|-------------------|------|
| B.1 | P0 | `core` items 1–10 | `com.estudos.core.imperative`, `.exceptions`, `.oop`, `.generics_collections` | Strings, pass-by-value, exceptions, `equals`/`hashCode`, collections, generics — **numbered track** in [`core/README-en.md`](core/README-en.md) |
| B.2 | P0 | `java8` | `com.estudos.java8.examples`, `com.estudos.java8.challenges` | Lambda, Stream, Optional — **before** `core` → `DeclarativeVsImperative` |
| B.3 | P0 | `core` items 11–14 | `com.estudos.core.declarative`, `.jvm`, `.concurrency` | Streams vs imperative, JVM memory, GC, threads |
| B.4 | P1 | `core` challenges | `com.estudos.core.challenges.*` | Anagram, `equals`/`hashCode`, thread-safe counter — after the topics listed in [`core/README-en.md`](core/README-en.md) |

### Phase C — JDK by version (incremental P2)

| # | Pri | Module | Typical packages | Focus |
|---|-----|--------|-----------------|------|
| C.1 | P1 | `java11` | `com.estudos.java11.examples`, `.challenges`, `.misc` | `var`, `HttpClient`, `Files`, single-source |
| C.2 | P2 | `java17` | `com.estudos.java17.examples`, `.challenges` | Records, sealed, pattern matching, text blocks |
| C.3 | P2 | `java21` | `com.estudos.java21.examples`, `.challenges` | Virtual threads, sequenced collections, record patterns |

### Phase D — JVM frameworks (`frameworks`)

| # | Pri | Maven module | Package | Focus |
|---|-----|--------------|--------|------|
| D.1 | P1 | `frameworks` (`java-frameworks-parent`) | `com.estudos.frameworks.springboot` / `.micronaut` / `.quarkus` (+ `api`, `application`, `domain`, `infrastructure`) | **REST**, layers, DTO, repository, DI; Micronaut and Quarkus in parallel — [`mvcerestcamadas-en.md`](frameworks/docs/mvcerestcamadas-en.md) |

**When:** after solid **language** (`basics-language`) and ideally **`java11`** (HTTP); can run in parallel with `java17`/`java21` depending on job needs.

**Re-read:** cross `basics-memory` and `basics-os-concurrency` with `core` → `JvmMemoryModelIntro` and `MultithreadingIntro` when theory clicks better with code.

---

## Maven commands

| Goal | Command (inside the module folder) |
|----------|-------------------------------------|
| Compile | `mvn -q compile` |
| Tests   | `mvn test` |
| Run an example | `mvn -q exec:java "-Dexec.mainClass=com.estudos.PACKAGE.Class"` |
| Spring Boot (`frameworks-springboot`) | `cd frameworks` · `mvn -q -pl frameworks-springboot spring-boot:run` (port 8080) |
| Micronaut | `cd frameworks` · `mvn -q -pl frameworks-micronaut mn:run` (port 8081) |
| Quarkus | `cd frameworks` · `mvn -q -pl frameworks-quarkus quarkus:dev` (port 8082) |

**PowerShell:** `-Dexec.mainClass=...` must be quoted (the dot breaks the parser if unquoted).

**JDK:** use the module version (**java21** requires JDK 21). **java8** uses **Mockito 4.11** in `pom.xml` for Java 8 compatibility; others use Mockito 5. The **`frameworks`** aggregator builds with **Java 17** in all three submodules (Spring Boot 3, Micronaut 4, Quarkus 3).

---

## Automated tests

Stack in all modules with tests:

- **JUnit 5** (Jupiter) — execution and lifecycle
- **AssertJ** — fluent assertions (`assertThat(...).isEqualTo(...)`)
- **Mockito** — mocks (`@Mock`, `when`, `verify`); `mockito-junit-jupiter` extension

### Where tests live (`src/test/java`)

| Module | Test classes (summary) |
|--------|---------------------------|
| `core` | `ChallengeEqualsHashCodeTest`, `ChallengeThreadSafeCounterTest`, `ChallengeAnagramTest`, `RelogioMockitoDemoTest` |
| `java8` | `ChallengeStreamWordCountTest`, `ChallengeCollectorsGroupingTest`, `PredicateMockitoDemoTest` |
| `java11` | `ChallengeHttpHeadTest` (mock `HttpClient`), `ChallengeReadResourceTest` (`@TempDir`) |
| `java17` | `ChallengeSealedSwitchTest`, `ChallengeRecordValidationTest` |
| `java21` | `ChallengeSequencedPipelineTest`, `ChallengeVirtualHttpTest` (mock + synchronous executor in test) |
| `frameworks` | `frameworks-springboot`: `ArticleApiTest`, `DefaultArticleServiceTest` · `frameworks-micronaut`: `ArticleApiTest` · `frameworks-quarkus`: `ArticleResourceIT` |

Versions: `frameworks-springboot` inherits *spring-boot-starter-parent*; Micronaut uses `io.micronaut.platform:micronaut-parent` (empty `relativePath`); Quarkus imports `quarkus-bom` in the submodule.

---

## Challenges (`challenges`) vs examples

- **Challenges:** there is a **reference solution** in main sources so `mvn test` passes. Each challenge’s Javadoc explains how to “revert” or vary for study (e.g. in `core`, comment out `hashCode` in `ChallengeEqualsHashCode`).
- **Examples:** teaching code with `main`. In **all** modules, each example includes **`meuPlayground()`** and **`// meuPlayground();`** in `main` — free space to experiment; **not** the same as official challenges.

---

## Concepts only in `core`

- **`package-info.java`:** package documentation (JavaDoc), not an executable class — explained in [`core/README-en.md`](core/README-en.md).
- **Paradigms** (`imperative`, `oop`, `declarative`, …): see table in [`core/README-en.md`](core/README-en.md).

---

## Next steps (outside `basics` in this repository)

Topics for a future module or documentation — tied to **DevOps**, **pipelines**, and **automation**; **not** expanded in `basics` right now:

- **Observability:** metrics (e.g. Prometheus), distributed tracing (OpenTelemetry), structured logs, SLI/SLO, alerts and dashboards.
- **CI/CD:** pipelines (GitHub Actions, GitLab CI, Jenkins), artifacts, promotion across environments, rollback.
- **Automation / infra as code:** Terraform, Ansible, Helm; policies, secrets in the pipeline, governance.

---

## Official reading

- [JLS](https://docs.oracle.com/javase/specs/jls/se17/html/) · [JVMS](https://docs.oracle.com/javase/specs/jvms/se17/html/)
