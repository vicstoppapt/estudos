# `basics-os-concurrency` — processo, threads, CPU / process, threads, CPU

**English:** [README-en.md](README-en.md)

Submódulo do agregador `basics`: **SO + hardware lógico** e encaixe com **JVM / threads Java**. Submodule of `basics`: **OS + logical hardware** and how it maps to the **JVM / Java threads**.

**Glossário PT ↔ EN:** [glossary-os-concurrency.md](src/main/java/com/estudos/basics/os/glossary-os-concurrency.md)

**Começar por / Start here:**

- PT: [processadorcoresethreads.md](src/main/java/com/estudos/basics/os/processadorcoresethreads.md)
- EN: [processadorcoresethreads-en.md](src/main/java/com/estudos/basics/os/processadorcoresethreads-en.md)

## Documentos / Documents

| PT | EN | Tema / Topic |
|----|----|----------------|
| [processadorcoresethreads.md](src/main/java/com/estudos/basics/os/processadorcoresethreads.md) | [processadorcoresethreads-en.md](src/main/java/com/estudos/basics/os/processadorcoresethreads-en.md) | Guia longo / Long guide: vocabulary, physical vs logical core, scheduler, sizing, containers |
| [processosthreadsecpu.md](src/main/java/com/estudos/basics/os/processosthreadsecpu.md) | [processosthreadsecpu-en.md](src/main/java/com/estudos/basics/os/processosthreadsecpu-en.md) | Resumo arquiteto / Architect summary |
| [jvmeagendamento.md](src/main/java/com/estudos/basics/os/jvmeagendamento.md) | [jvmeagendamento-en.md](src/main/java/com/estudos/basics/os/jvmeagendamento-en.md) | JVM, thread Java vs OS, virtual threads |
| [glossary-os-concurrency.md](src/main/java/com/estudos/basics/os/glossary-os-concurrency.md) | *(bilingual)* | Termos PT ↔ EN / PT ↔ EN terms |

## Código / Code

| Class | `exec:mainClass` |
|-------|------------------|
| `ProcessorThreadExamples` | `com.estudos.basics.os.ProcessorThreadExamples` |
| `OsRuntimeSnapshot` | `com.estudos.basics.os.OsRuntimeSnapshot` |

## Como executar tudo / How to run everything

**Requisito / Prerequisite:** JDK **17+** (alinhado ao POM pai em `basics/` / aligned with parent POM under `basics/`).

### A) A partir da pasta `basics` (recomendado / recommended)

Caminho típico do clone: `…/estudos/basics`. / Typical clone path: `…/estudos/basics`.

```bash
cd basics
mvn -q -pl basics-os-concurrency compile
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

Compilar **todo** o agregador `basics` (todos os submódulos): / Compile **all** `basics` modules:

```bash
cd basics
mvn -q compile
```

### B) Só `basics-os-concurrency` / This submodule only

(Primeiro `cd basics` a partir da raiz do repositório, se ainda não estiveres nessa pasta.) / *(Run `cd basics` from the repo root if needed.)*

```bash
cd basics-os-concurrency
mvn -q compile
mvn -q exec:java "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

**PowerShell:** mantém aspas em `-Dexec.mainClass=...`. / **PowerShell:** keep quotes around `-Dexec.mainClass=...`.

**IDE:** Run `main` in `ProcessorThreadExamples` or `OsRuntimeSnapshot` with this module on the classpath.

## Relacionado neste repositório / Related in this repo

- `basics-memory` → stack por thread, cache CPU / per-thread stack, CPU cache.
- `core` → `MultithreadingIntro`, `JvmMemoryModelIntro`.
- `java21` → virtual threads (JDK).
