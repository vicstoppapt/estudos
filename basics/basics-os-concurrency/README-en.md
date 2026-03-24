**PT:** [README.md](README.md)

# `basics-os-concurrency` — process, threads, CPU

Submodule of the `basics` aggregator: **OS + logical hardware** and how it maps to the **JVM / Java threads**.

**PT ↔ EN glossary:** [glossary-os-concurrency-en.md](src/main/java/com/estudos/basics/os/glossary-os-concurrency-en.md) · Portuguese: [glossary-os-concurrency.md](src/main/java/com/estudos/basics/os/glossary-os-concurrency.md)

**Start here:**

- PT: [processadorcoresethreads.md](src/main/java/com/estudos/basics/os/processadorcoresethreads.md)
- EN: [processadorcoresethreads-en.md](src/main/java/com/estudos/basics/os/processadorcoresethreads-en.md)

## Documents

| PT | EN | Topic |
|----|----|----------------|
| [processadorcoresethreads.md](src/main/java/com/estudos/basics/os/processadorcoresethreads.md) | [processadorcoresethreads-en.md](src/main/java/com/estudos/basics/os/processadorcoresethreads-en.md) | Long guide: vocabulary, physical vs logical core, scheduler, sizing, containers |
| [processosthreadsecpu.md](src/main/java/com/estudos/basics/os/processosthreadsecpu.md) | [processosthreadsecpu-en.md](src/main/java/com/estudos/basics/os/processosthreadsecpu-en.md) | Architect summary |
| [jvmeagendamento.md](src/main/java/com/estudos/basics/os/jvmeagendamento.md) | [jvmeagendamento-en.md](src/main/java/com/estudos/basics/os/jvmeagendamento-en.md) | JVM, Java thread vs OS, virtual threads |
| [glossary-os-concurrency.md](src/main/java/com/estudos/basics/os/glossary-os-concurrency.md) | [glossary-os-concurrency-en.md](src/main/java/com/estudos/basics/os/glossary-os-concurrency-en.md) | PT ↔ EN terms |

## Code

| Class | `exec:mainClass` |
|-------|------------------|
| `ProcessorThreadExamples` | `com.estudos.basics.os.ProcessorThreadExamples` |
| `OsRuntimeSnapshot` | `com.estudos.basics.os.OsRuntimeSnapshot` |

## How to run everything

**Prerequisite:** JDK **17+** (aligned with parent POM under `basics/`).

### A) From the `basics` folder (recommended)

Typical clone path: `…/estudos/basics`.

```bash
cd basics
mvn -q -pl basics-os-concurrency compile
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

Compile the whole `basics` aggregator (all submodules):

```bash
cd basics
mvn -q compile
```

### B) `basics-os-concurrency` only

(`cd basics` from the repo root first if needed.)

```bash
cd basics-os-concurrency
mvn -q compile
mvn -q exec:java "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

**PowerShell:** keep quotes around `-Dexec.mainClass=...`.

**IDE:** Run `main` in `ProcessorThreadExamples` or `OsRuntimeSnapshot` with this module on the classpath.

## Related in this repo

- `basics-memory` → per-thread stack, CPU cache.
- `core` → `MultithreadingIntro`, `JvmMemoryModelIntro`.
- `java21` → virtual threads (JDK).
