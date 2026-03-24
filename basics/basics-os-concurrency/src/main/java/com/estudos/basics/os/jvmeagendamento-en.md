# JVM, single process, and Java threads

**Language:** [Português](jvmeagendamento.md) · **Glossary:** [glossary-os-concurrency.md](glossary-os-concurrency.md)

Companion to [processosthreadsecpu-en.md](processosthreadsecpu-en.md) and [processadorcoresethreads-en.md](processadorcoresethreads-en.md). Focus: **what happens when you call `new Thread(...).start()`** and how that relates to **cores**.

---

## How to run

Same Maven examples as in the [detailed guide](processadorcoresethreads-en.md#how-to-run): `ProcessorThreadExamples` and `OsRuntimeSnapshot` in `com.estudos.basics.os`.

---

## 1. One JVM ≈ one OS process (simplified)

In the typical setup, **one** instance of your Java app is **one** OS process. Inside it:

- **Heap** and **metaspace** (class metadata) are **shared** by all Java threads.
- Each **Java thread** has its **own stack** (method frames, locals, references).

This matches `basics-memory` → [memoryandreferences.md](../../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/memoryandreferences.md).

---

## 2. Java thread and OS thread (classic model)

On **traditional** implementations (**platform thread**), a Java thread **maps** to an **OS thread** (often 1:1). Therefore:

- **Creating** many Java threads ≈ creating many OS threads → scheduler pressure and stack memory.
- **Blocking** on I/O (socket read, JDBC) **blocks** that OS thread.

---

## 3. Virtual threads (JDK 21+)

**Virtual threads** are cheap in the JVM: when they block on I/O, the runtime can **unmount** the continuation and use **few** OS threads for many logical tasks. They are **not** magic for pure **CPU-bound** work — you still need cores and a suitable parallel algorithm.

Code study: **`java21`** module in this repo (`VirtualThreadsExample`, challenges).

---

## 4. What to study in `core`

| Suggested order | Class / doc |
|-----------------|-------------|
| Memory model | `JvmMemoryModelIntro` / `jvmmemorymodelintro.md` |
| Practical concurrency | `MultithreadingIntro` / `multithreadingintro.md` |
| Challenge | `ChallengeThreadSafeCounter` |

`basics-os-concurrency` gives **OS vocabulary**; `core` gives **Java semantics** (visibility, locks, APIs).

---

## 5. Useful phrases in architecture reviews

- *“Java runs in one **process**; threads share the **heap**; each thread has its **own stack**.”*
- *“Platform thread ≈ OS thread; a **pool** limits fan-out to the scheduler.”*
- *“**Virtual threads** help **I/O-bound** work; **CPU-bound** scales with **cores** and algorithm design.”*
