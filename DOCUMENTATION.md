# Documentação e execução — repositório `estudos`

**English:** [DOCUMENTATION-en.md](DOCUMENTATION-en.md)

**Índice:** [README.md](README.md) (plano de estudos) · [GLOSSARY.md](GLOSSARY.md) · [GLOSSARY-en.md](GLOSSARY-en.md)

## Idioma da documentação

| Área | PT | EN |
|------|----|----|
| `basics-os-concurrency` | `.md` + [glossary-os-concurrency.md](basics/basics-os-concurrency/src/main/java/com/estudos/basics/os/glossary-os-concurrency.md) | pares `*-en.md` + [glossary-os-concurrency-en.md](basics/basics-os-concurrency/src/main/java/com/estudos/basics/os/glossary-os-concurrency-en.md) |
| `basics` (outros submódulos), `core`, `java8`–`java21` | `.md` junto ao código e `README.md` | ficheiro espelho `*-en.md` (mesmo nome base) |
| `frameworks/docs` | teoría em PT | pares `*-en.md` no mesmo diretório |

---

## Pré-requisitos

- **JDK:** 8 para `java8`; 11 para `java11`; **17** para `basics`, `core`, `java17`, `frameworks`; **21** para `java21`.
- **Maven 3.8+** no `PATH`.
- Nos exemplos abaixo, **`estudos/`** é a pasta que contém `basics/`, `core/`, `pom.xml` ou agregadores (alguns módulos são POM próprio sem pai comum no topo — cada um compila isolado).

**PowerShell (Windows):** mantém aspas em `-Dexec.mainClass=...`.

---

## Como executar — visão por módulo

Todas as linhas assumem que já fizeste `cd` para a pasta **`estudos`** (raiz deste repositório de estudos), salvo indicação.

### `basics` (agregador Maven)

| Objetivo | Comando |
|----------|---------|
| Compilar **todos** os submódulos | `cd basics` · `mvn -q compile` |
| Linguagem: variáveis | `cd basics` · `mvn -q exec:java -pl basics-language "-Dexec.mainClass=com.estudos.basics.VariablesAndTypes"` |
| Memória: referências | `mvn -q exec:java -pl basics-memory "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"` |
| SO/CPU: exemplos | `mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"` · idem `OsRuntimeSnapshot` |
| Rede | `mvn -q exec:java -pl basics-networking "-Dexec.mainClass=com.estudos.basics.network.NetworkingLiteracy"` |
| Segurança | `mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.CryptoLiteracy"` · `SqlInjectionConcept` |
| Armazenamento | `mvn -q exec:java -pl basics-storage "-Dexec.mainClass=com.estudos.basics.storage.StorageLiteracy"` |

Detalhe PT/EN e variantes: [basics/basics-os-concurrency/README.md](basics/basics-os-concurrency/README.md) · índice geral: [basics/README.md](basics/README.md).

### `core`

| Objetivo | Comando |
|----------|---------|
| Testes | `cd core` · `mvn -q test` |
| Exemplo (ajusta a classe) | `cd core` · `mvn -q compile exec:java "-Dexec.mainClass=com.estudos.core.imperative.StringImmutability"` |

Trilha completa das classes: [core/README.md](core/README.md).

### `java8`

| Objetivo | Comando |
|----------|---------|
| Exemplo | `cd java8` · `mvn -q compile exec:java -Dexec.mainClass="com.estudos.java8.examples.LambdaBasics"` |
| Testes | `mvn -q test` |

### `java11`

| Objetivo | Comando |
|----------|---------|
| Exemplo Maven | `cd java11` · `mvn -q compile exec:java -Dexec.mainClass="com.estudos.java11.examples.VarInference"` |
| Ficheiro único (sem Maven) | `cd java11/src/main/java/com/estudos/java11/misc` · `java Ola.java` |
| Testes | `cd java11` · `mvn -q test` |

### `java17`

| Objetivo | Comando |
|----------|---------|
| Exemplo | `cd java17` · `mvn -q compile exec:java -Dexec.mainClass="com.estudos.java17.examples.RecordsExample"` |
| Testes | `mvn -q test` |

### `java21`

| Objetivo | Comando |
|----------|---------|
| Exemplo | `cd java21` · `mvn -q compile exec:java -Dexec.mainClass="com.estudos.java21.examples.VirtualThreadsExample"` |
| Testes | `mvn -q test` |

**JDK 21 obrigatório** neste módulo.

### `frameworks` (Spring Boot, Micronaut, Quarkus)

| Objetivo | Comando |
|----------|---------|
| Testes (reactor) | `cd frameworks` · `mvn -q test` |
| Spring Boot (8080) | `cd frameworks` · `mvn -q -pl frameworks-springboot spring-boot:run` |
| Micronaut (8081) | `mvn -q -pl frameworks-micronaut mn:run` |
| Quarkus dev (8082) | `mvn -q -pl frameworks-quarkus quarkus:dev` |

Teoria: [frameworks/README.md](frameworks/README.md) · `frameworks/docs/`.

---

## Mapa de `README.md` por pasta

| Pasta | Ficheiro |
|-------|----------|
| Raiz | [README.md](README.md) |
| basics | [basics/README.md](basics/README.md) |
| basics-os-concurrency | [basics/basics-os-concurrency/README.md](basics/basics-os-concurrency/README.md) |
| core | [core/README.md](core/README.md) |
| java8 … java21 | `java*/README.md` |
| frameworks | [frameworks/README.md](frameworks/README.md) |

---

# Documentation & how to run — `estudos` repository

**Index:** [README-en.md](README-en.md) (study plan) · [GLOSSARY-en.md](GLOSSARY-en.md) (PT↔EN terms) · **Portuguese:** [DOCUMENTATION.md](DOCUMENTATION.md) (top of file)

## Documentation language

| Area | Portuguese | English |
|------|------------|---------|
| `basics-os-concurrency` | `.md` + glossary | sibling `*-en.md` + [glossary-os-concurrency-en.md](basics/basics-os-concurrency/src/main/java/com/estudos/basics/os/glossary-os-concurrency-en.md) |
| Other `basics`, `core`, `java8`–`java21` | `.md` next to sources | sibling `*-en.md` |
| `frameworks/docs` | prose in PT | sibling `*-en.md` |

## Prerequisites

- **JDK:** 8 (`java8`), 11 (`java11`), 17 (`basics`, `core`, `java17`, `frameworks`), 21 (`java21`).
- **Maven 3.8+** on `PATH`.
- Commands below assume your current directory is **`estudos`** unless noted.

**PowerShell:** keep quotes around `-Dexec.mainClass=...`.

## How to run — by module

### `basics`

| Goal | Command |
|------|---------|
| Compile **all** submodules | `cd basics` · `mvn -q compile` |
| Language example | `cd basics` · `mvn -q exec:java -pl basics-language "-Dexec.mainClass=com.estudos.basics.VariablesAndTypes"` |
| Memory | `mvn -q exec:java -pl basics-memory "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"` |
| OS/CPU samples | `mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"` · same for `OsRuntimeSnapshot` |
| Networking / Security / Storage | see [basics/README-en.md](basics/README-en.md) |

### `core`

`cd core` · `mvn -q test` · or `mvn -q compile exec:java "-Dexec.mainClass=com.estudos.core.imperative.StringImmutability"` (change class as needed). Full list: [core/README-en.md](core/README-en.md).

### `java8` / `java11` / `java17` / `java21`

See each `java*/README-en.md`. Pattern: `cd javaNN` · `mvn -q compile exec:java -Dexec.mainClass="fully.qualified.Name"` · `mvn -q test`.

### `frameworks`

`cd frameworks` · `mvn -q test` · run Spring / Micronaut / Quarkus as in [frameworks/README-en.md](frameworks/README-en.md).

## README map

| Folder | File (EN) |
|--------|-----------|
| Root | [README-en.md](README-en.md) |
| basics | [basics/README-en.md](basics/README-en.md) |
| basics-os-concurrency | [basics/basics-os-concurrency/README-en.md](basics/basics-os-concurrency/README-en.md) |
| core | [core/README-en.md](core/README-en.md) |
| java8 … java21 | `java*/README-en.md` |
| frameworks | [frameworks/README-en.md](frameworks/README-en.md) |
