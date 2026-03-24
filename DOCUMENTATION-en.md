# Documentation and how to run — `estudos` repository

**PT:** [DOCUMENTATION.md](DOCUMENTATION.md)

**Index:** [README-en.md](README-en.md) (study plan) · [GLOSSARY-en.md](GLOSSARY-en.md) (PT↔EN terms across the repo)

## Documentation language

| Area | Portuguese | English |
|------|------------|---------|
| `basics-os-concurrency` | `.md` + [glossary-os-concurrency.md](basics/basics-os-concurrency/src/main/java/com/estudos/basics/os/glossary-os-concurrency.md) | sibling `*-en.md` + [glossary-os-concurrency-en.md](basics/basics-os-concurrency/src/main/java/com/estudos/basics/os/glossary-os-concurrency-en.md) |
| Other `basics`, `core`, `java8`–`java21` | `.md` next to sources and `README.md` | sibling `*-en.md` (same basename) |
| `frameworks/docs` | prose in `.md` | sibling `*-en.md` |

---

## Prerequisites

- **JDK:** 8 (`java8`), 11 (`java11`), **17** (`basics`, `core`, `java17`, `frameworks`), **21** (`java21`).
- **Maven 3.8+** on `PATH`.
- In the examples below, **`estudos/`** is the folder that contains `basics/`, `core/`, `pom.xml`, or aggregators (some modules are standalone POMs — each compiles on its own).

**PowerShell (Windows):** keep quotes around `-Dexec.mainClass=...`.

---

## How to run — by module

All commands assume you have `cd`’d into **`estudos`** unless noted.

### `basics` (Maven aggregator)

| Goal | Command |
|------|---------|
| Compile **all** submodules | `cd basics` · `mvn -q compile` |
| Language: variables | `cd basics` · `mvn -q exec:java -pl basics-language "-Dexec.mainClass=com.estudos.basics.VariablesAndTypes"` |
| Memory: references | `mvn -q exec:java -pl basics-memory "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"` |
| OS/CPU samples | `mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"` · same for `OsRuntimeSnapshot` |
| Networking | `mvn -q exec:java -pl basics-networking "-Dexec.mainClass=com.estudos.basics.network.NetworkingLiteracy"` |
| Security | `mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.CryptoLiteracy"` · `SqlInjectionConcept` |
| Storage | `mvn -q exec:java -pl basics-storage "-Dexec.mainClass=com.estudos.basics.storage.StorageLiteracy"` |

PT/EN detail and variants: [basics/basics-os-concurrency/README-en.md](basics/basics-os-concurrency/README-en.md) · overview: [basics/README-en.md](basics/README-en.md).

### `core`

| Goal | Command |
|------|---------|
| Tests | `cd core` · `mvn -q test` |
| Sample (change the class) | `cd core` · `mvn -q compile exec:java "-Dexec.mainClass=com.estudos.core.imperative.StringImmutability"` |

Full class trail: [core/README-en.md](core/README-en.md).

### `java8`

| Goal | Command |
|------|---------|
| Example | `cd java8` · `mvn -q compile exec:java -Dexec.mainClass="com.estudos.java8.examples.LambdaBasics"` |
| Tests | `mvn -q test` |

### `java11`

| Goal | Command |
|------|---------|
| Example (Maven) | `cd java11` · `mvn -q compile exec:java -Dexec.mainClass="com.estudos.java11.examples.VarInference"` |
| Single file (no Maven) | `cd java11/src/main/java/com/estudos/java11/misc` · `java Ola.java` |
| Tests | `cd java11` · `mvn -q test` |

### `java17`

| Goal | Command |
|------|---------|
| Example | `cd java17` · `mvn -q compile exec:java -Dexec.mainClass="com.estudos.java17.examples.RecordsExample"` |
| Tests | `mvn -q test` |

### `java21`

| Goal | Command |
|------|---------|
| Example | `cd java21` · `mvn -q compile exec:java -Dexec.mainClass="com.estudos.java21.examples.VirtualThreadsExample"` |
| Tests | `mvn -q test` |

**JDK 21 required** for this module.

### `frameworks` (Spring Boot, Micronaut, Quarkus)

| Goal | Command |
|------|---------|
| Tests (reactor) | `cd frameworks` · `mvn -q test` |
| Spring Boot (8080) | `cd frameworks` · `mvn -q -pl frameworks-springboot spring-boot:run` |
| Micronaut (8081) | `mvn -q -pl frameworks-micronaut mn:run` |
| Quarkus dev (8082) | `mvn -q -pl frameworks-quarkus quarkus:dev` |

Theory: [frameworks/README-en.md](frameworks/README-en.md) · `frameworks/docs/*-en.md`.

---

## README map

| Folder | File |
|--------|------|
| Root | [README-en.md](README-en.md) |
| basics | [basics/README-en.md](basics/README-en.md) |
| basics-os-concurrency | [basics/basics-os-concurrency/README-en.md](basics/basics-os-concurrency/README-en.md) |
| core | [core/README-en.md](core/README-en.md) |
| java8 … java21 | `java*/README-en.md` |
| frameworks | [frameworks/README-en.md](frameworks/README-en.md) |
