# `basics-os-concurrency` — processo, threads, CPU

Submódulo do agregador `basics`: **SO + hardware lógico** (processo, thread de SO, núcleos, agendamento) e como isso **encaixa** com **JVM / threads Java**. Útil para arquitetos que precisam falar com operações, dimensionar serviços e entender limites.

**Começar por:** [processadorcoresethreads.md](src/main/java/com/estudos/basics/os/processadorcoresethreads.md) (guia detalhado: o que é core, o que é thread, como calcular pools, quem chama o quê).

## Documentos

| Ficheiro | Tema |
|----------|------|
| [processadorcoresethreads.md](src/main/java/com/estudos/basics/os/processadorcoresethreads.md) | **Guia longo:** vocabulário, núcleo físico vs lógico, scheduler, fórmulas de dimensionamento, contentores |
| [processosthreadsecpu.md](src/main/java/com/estudos/basics/os/processosthreadsecpu.md) | Resumo: processo vs thread, núcleos, context switch, I/O |
| [jvmeagendamento.md](src/main/java/com/estudos/basics/os/jvmeagendamento.md) | JVM como processo SO, thread Java vs thread de SO, virtual threads |

## Código

| Classe | `exec:mainClass` |
|--------|------------------|
| `ProcessorThreadExamples` | `com.estudos.basics.os.ProcessorThreadExamples` |
| `OsRuntimeSnapshot` | `com.estudos.basics.os.OsRuntimeSnapshot` |

## Maven

Na pasta **`basics`**:

```bash
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

## Relacionado neste repositório

- `basics-memory` → stack por thread, cache CPU.
- `core` → `MultithreadingIntro`, `JvmMemoryModelIntro`.
- `java21` → virtual threads (detalhe de produto JDK).
