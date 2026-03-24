# Glossário — SO, CPU, threads e JVM (PT ↔ EN) · Glossary (PT ↔ EN)

**English:** [glossary-os-concurrency-en.md](glossary-os-concurrency-en.md)

**Idioma / Language:** uma única tabela com colunas **English** e **Portuguese**; definições curtas em inglês. / Single table with **English** and **Portuguese** columns; short definitions in English.

**Glossário mais largo (linguagem, rede, frameworks, …):** [GLOSSARY.md](../../../../../../../../../GLOSSARY.md) na raiz de `estudos`. **Como correr todos os módulos:** [DOCUMENTATION.md](../../../../../../../../../DOCUMENTATION.md).

Índice rápido dos termos usados nos pares PT/EN: [processadorcoresethreads.md](processadorcoresethreads.md) · [processadorcoresethreads-en.md](processadorcoresethreads-en.md), [processosthreadsecpu.md](processosthreadsecpu.md) · [processosthreadsecpu-en.md](processosthreadsecpu-en.md), [jvmeagendamento.md](jvmeagendamento.md) · [jvmeagendamento-en.md](jvmeagendamento-en.md).

---

## Por termo em inglês (A–Z)

| English | Português | Short definition (EN) |
|---------|-----------|------------------------|
| **address space** (virtual) | espaço de endereçamento (virtual) | Memory range a process may use; isolated per process. |
| **async / asynchronous** | assíncrono | Style where I/O does not block a thread for the full wait (callbacks, futures, reactive). |
| **available processors** | processadores disponíveis | `Runtime.getRuntime().availableProcessors()` — logical CPUs visible to the JVM for scheduling work. |
| **cgroup** | cgroup | Linux control group: limits CPU/memory visible or enforceable for a process/container. |
| **context switch** | troca de contexto | Saving one thread’s CPU state and loading another’s; has cache and time cost. |
| **contention** | contenção | Many threads competing for the same lock or resource. |
| **core** (physical) | núcleo (físico) | Independent execution unit on the die; runs one instruction stream at a time (without SMT duplication). |
| **core** (logical) | núcleo / CPU (lógico) | What the OS counts as an independent schedulable CPU (includes SMT siblings). |
| **CPU** / **processor** | CPU / processador | Circuit that executes machine instructions. |
| **CPU-bound** | limitado por CPU / CPU-bound | Work dominated by computation; scaling follows cores more than thread count. |
| **Docker** | Docker | Container runtime (often uses cgroups/namespaces on Linux). |
| **file descriptor** | descritor de ficheiro | OS handle for an open file, socket, pipe, etc. |
| **heap** | heap | Shared object memory in the JVM; all Java threads in a process share it. |
| **Hyper-Threading** (Intel) | Hyper-Threading | Intel’s SMT brand: two logical CPUs per physical core. |
| **I/O-bound** | limitado por I/O / I/O-bound | Work dominated by waiting on disk/network/DB. |
| **IPC** | IPC (comunicação entre processos) | Inter-process communication: pipes, sockets, shared memory, etc. |
| **JIT** (compiler) | JIT (compilador) | Just-in-time compilation: JVM compiles bytecode to native code at runtime. |
| **kernel** | kernel / núcleo do SO | Operating-system core: scheduler, memory management, drivers. |
| **Kubernetes** | Kubernetes | Orchestrator for containers; CPU **requests/limits** affect effective quota. |
| **lock** | lock / exclusão mútua | Synchronization primitive; high contention hurts throughput. |
| **logical processor** / **logical CPU** | processador lógico / CPU lógica | OS-visible CPU index used by the scheduler (may be SMT sibling). |
| **metaspace** | metaspace | JVM area for class metadata (replaces permgen in modern Java). |
| **multiplexing** (time) | multiplexagem (no tempo) | Running many threads on fewer cores by switching over time. |
| **OS thread** / **kernel thread** | thread de SO / thread do kernel | Schedulable unit inside a process, managed by the kernel. |
| **parallelism** | paralelismo | Instructions actually executing at the same instant on different cores. |
| **PID** | PID | Process identifier. |
| **pipe** | pipe | IPC channel between processes. |
| **platform thread** | thread de plataforma | Classic Java thread mapped ~1:1 to an OS thread (HotSpot tradition). |
| **preemption** | preempção | Kernel interrupting a running thread to run another. |
| **process** | processo | Running program instance with isolated address space and resources. |
| **quantum** (time slice) | quantum / fatia de tempo | How long a thread may run before the scheduler reconsidered. |
| **requests / limits** (K8s) | requests / limits | CPU/memory reservation and cap for a pod/container. |
| **runnable** | pronta / runnable | Thread state: ready to run, waiting for a CPU. |
| **scheduler** | scheduler / agendador | Kernel component that decides which thread runs on which CPU. |
| **socket** (CPU) | socket (CPU) | Physical CPU package slot on the motherboard. |
| **SMT** (simultaneous multithreading) | SMT | Two logical threads share one physical core’s execution resources. |
| **stack** (per thread) | stack (por thread) | Per-thread call stack (local variables, frames); not shared between threads like heap. |
| **thread pool** | pool de threads | Fixed or bounded set of worker threads (e.g. `ExecutorService`). |
| **throughput** | débito / throughput | Work completed per unit time. |
| **virtual memory** | memória virtual | Abstraction of address space backed by RAM and swap/page file. |
| **virtual thread** | virtual thread (thread virtual) | Lightweight Java thread (JDK 21+); many map to few OS threads for I/O. |
| **JVM** | JVM | Java Virtual Machine; runs inside one OS process in the usual setup. |
| **L1 / L2 / L3 cache** | cache L1 / L2 / L3 | CPU caches; context switches hurt locality here. |

---

## APIs e classes citadas

| English context | Português | Notes |
|-----------------|-----------|--------|
| `Runtime.getRuntime().availableProcessors()` | núcleos lógicos visíveis à JVM | May not match container CPU quota. |
| `ForkJoinPool.commonPool()` | pool partilhado para `parallelStream` etc. | Parallelism tied to processor count. |
| `ExecutorService` / fixed thread pool | serviço executor / pool fixo | Size often ≈ `n` or `n+1` for CPU-bound. |
| `new Thread(...).start()` | criar thread de plataforma | Maps to OS thread cost. |
| `ProcessHandle` | identificação do processo atual | `pid`, command line (Java 9+). |

---

## Índice inverso (PT → EN)

| Português (usual) | English |
|-------------------|---------|
| agendador / scheduler | **scheduler** |
| contenção | **contention** |
| descritor de ficheiro | **file descriptor** |
| espaço de endereçamento | **address space** |
| heap | **heap** |
| limitado por CPU | **CPU-bound** |
| limitado por I/O | **I/O-bound** |
| núcleo (físico / lógico) | **physical / logical core** (or **logical processor**) |
| pool de threads | **thread pool** |
| pré-empção | **preemption** |
| processador / CPU | **CPU** / **processor** |
| processo | **process** |
| quantum / fatia de tempo | **quantum** / **time slice** |
| stack (por thread) | **stack** (per thread) |
| thread de plataforma | **platform thread** |
| thread de SO / do kernel | **OS thread** / **kernel thread** |
| thread virtual | **virtual thread** |
| troca de contexto | **context switch** |

*(Para mais entradas, vê a tabela principal ordenada por inglês.)*

---

## Como executar os exemplos

- PT: [processadorcoresethreads.md § Como executar](processadorcoresethreads.md#como-executar)
- EN: [processadorcoresethreads-en.md § How to run](processadorcoresethreads-en.md#how-to-run)
- Índice do submódulo: [README](../../../../../../../README.md)

Na documentação e entrevistas em inglês, usa as formas da coluna **English** da tabela principal.
