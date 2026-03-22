# `basics-os-concurrency` — processo, threads, CPU

Submódulo do agregador `basics`: **SO + hardware lógico** (processo, thread de SO, núcleos, agendamento) e como isso **encaixa** com **JVM / threads Java**. Útil para arquitetos que precisam falar com operações, dimensionar serviços e entender limites.

## Documentos

| Ficheiro | Tema |
|----------|------|
| [processosthreadsecpu.md](src/main/java/com/estudos/basics/os/processosthreadsecpu.md) | Processo vs thread, núcleos, context switch, I/O |
| [jvmeagendamento.md](src/main/java/com/estudos/basics/os/jvmeagendamento.md) | JVM como processo SO, thread Java vs thread de SO, ligação ao `core` |

## Código

| Classe | `exec:mainClass` |
|--------|------------------|
| `OsRuntimeSnapshot` | `com.estudos.basics.os.OsRuntimeSnapshot` |

## Maven

Na pasta **`basics`**:

```bash
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

## Relacionado neste repositório

- `basics-memory` → stack por thread, cache CPU.
- `core` → `MultithreadingIntro`, `JvmMemoryModelIntro`.
- `java21` → virtual threads (detalhe de produto JDK).
