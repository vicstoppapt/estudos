# Processes, OS threads, and CPU (architect view)

**Language:** [Português](processosthreadsecpu.md) · **Glossary:** [glossary-os-concurrency.md](glossary-os-concurrency.md)

**Detail first:** vocabulary, pool sizing, hardware→OS→JVM chain — [processadorcoresethreads-en.md](processadorcoresethreads-en.md) · samples: [`ProcessorThreadExamples.java`](ProcessorThreadExamples.java).

Submodule **`basics-os-concurrency`**. Supporting code: [`OsRuntimeSnapshot.java`](OsRuntimeSnapshot.java). Memory and cache: `basics-memory` → [cpucachejvmenavegador.md](../../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/cpucachejvmenavegador.md). Java threads (race, `synchronized`): `core` → `MultithreadingIntro`.

---

## How to run

Same as the detailed guide: [How to run in processadorcoresethreads-en.md](processadorcoresethreads-en.md#how-to-run) — compile `basics-os-concurrency` and run `ProcessorThreadExamples` or `OsRuntimeSnapshot`.

---

## 1. Process (OS)

A **process** is a running program instance **isolated** by the OS: its own **virtual address space**, file descriptors, user/permissions, etc. Two processes **do not** share application memory by default (they communicate via pipes, sockets, files, IPC).

- **Creating a process** is **heavy** (relatively): the OS allocates structures, page tables, loads the image, etc.
- **Termination** releases those resources.

When you run `java -jar app.jar`, the OS creates **one process** whose executable image is the JVM (your app runs **inside** that process).

---

## 2. Operating-system thread

An **OS thread** is a **sequence of execution** inside a **process**. Threads in the **same** process share the **same address space** (process heap, native globals, etc.) but each has its **own stack** and **registers**.

- Creating an OS thread is **cheaper** than creating a process, but not free: kernel structures, preemption, etc.
- **Multiple threads** may run **in parallel** on **multiple cores**; on a single core the OS **time-multiplexes** (each runs for a slice).

---

## 3. Cores and logical processors

A **CPU package** can contain several **physical cores**. **Hyper-Threading** (Intel) or **SMT** (AMD) exposes **two logical processors** per physical core on some chips — the OS sees “more CPUs” to schedule.

- `Runtime.getRuntime().availableProcessors()` in Java returns what the JVM considers **logical cores** for **scheduling** (may be affected by **cgroups** in containers).
- **Architecture:** do not confuse “10k HTTP req/s” with “10k OS threads” — each thread consumes stack memory and scheduler load.

---

## 4. Scheduling and context switching

The OS **scheduler** decides **which thread** runs on **which core** and for how long. **Preemption** interrupts one thread to run another.

**Context switch:** save one thread’s state (registers, stack pointer, etc.) and load another’s. Costs:

- Loss of **locality** in L1/L2/L3 caches (another thread brings different data).
- CPU time spent **only** switching tasks.

So **very many** competing OS threads can **hurt** performance — hence fixed **pools**, **virtual threads** (JDK 21+) for I/O, or **async** with few threads.

---

## 5. CPU-bound vs I/O-bound work

| Type | What it does | Implication |
|------|--------------|-------------|
| **CPU-bound** | Computes most of the time | Scales with **cores**; more threads than cores does not speed pure compute |
| **I/O-bound** | Waits on disk, network, DB | Threads may **block** waiting; virtual threads / async reduce wasted OS threads |

---

## 6. Containers and limits

In **Kubernetes** / Docker, the process sometimes sees the “whole” machine in `availableProcessors`, but **cgroups** may **cap** effective CPUs — sizing and load tests should align with **requests/limits**, not only the node’s core count.

---

## 7. Where to go deeper

| Target | Topic |
|--------|--------|
| [jvmeagendamento-en.md](jvmeagendamento-en.md) | JVM, Java thread, virtual threads (bridge) |
| `basics-memory` | Per-thread stack, shared heap |
| `core` → `multithreadingintro.md` | `synchronized`, `AtomicInteger`, visibility |
| `java21` | Virtual threads in code |
