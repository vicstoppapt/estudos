# Processor, cores, and threads — detailed guide

**Language:** [Português](processadorcoresethreads.md) · **PT ↔ EN glossary:** [glossary-os-concurrency.md](glossary-os-concurrency.md)

Read this **before** diving into the JVM and concurrency code: precise vocabulary, how each layer **calls** the next, how to **count** resources, and how to **size** thread usage. Complements the summary in [processosthreadsecpu-en.md](processosthreadsecpu-en.md) and [jvmeagendamento-en.md](jvmeagendamento-en.md). Runnable samples: [`ProcessorThreadExamples.java`](ProcessorThreadExamples.java), [`OsRuntimeSnapshot.java`](OsRuntimeSnapshot.java).

---

## How to run

**Prerequisite:** JDK 17+ (same `release` as the parent POM under `basics/`).

1. **From the `basics` aggregator directory** (typical path: `…/estudos/basics`):

```bash
mvn -q -pl basics-os-concurrency compile
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

2. **Inside `basics-os-concurrency` only** (no `-pl`):

```bash
cd basics-os-concurrency
mvn -q compile
mvn -q exec:java "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

**PowerShell (Windows):** keep quotes around `-Dexec.mainClass=…` as above. **IDE:** run the `main` method in `ProcessorThreadExamples` or `OsRuntimeSnapshot` with module `basics-os-concurrency` on the classpath.

---

## 1. Vocabulary (hardware → OS → process)

| English | Portuguese (usual) | What it is |
|---------|-------------------|------------|
| **CPU** / **processor** | processador, CPU | Circuit that executes machine instructions. A typical PC has **one chip** (socket) with several cores. |
| **Socket** | encaixe, socket | Physical CPU slot on the motherboard. Desktops often have 1; servers may have several. |
| **Physical core** | núcleo físico | **Real** execution unit on the die: runs **one** program instruction stream at a time per core, except under SMT. |
| **Logical processor** / **logical CPU** | CPU lógica, núcleo lógico | What the **OS enumerates** as “CPU 0, CPU 1, …”. With **SMT** (Intel Hyper-Threading, AMD SMT), **one** physical core exposes **two** logical CPUs. |
| **SMT / Hyper-Threading** | “two threads per core” | Shares more pipeline resources between two logical sequences. Typical gain **below 2×** on mixed work; modest on homogeneous CPU-bound work. |
| **Process** | processo | **Isolated** running program: own address space, PID, kernel tables, etc. |
| **OS thread** | thread de sistema, kernel thread | **Schedulable** execution unit **inside** a process; shares memory with sibling threads; own stack and registers. |

In everyday speech **“core”** may mean **physical** or **logical**. For sizing and APIs like `Runtime.getRuntime().availableProcessors()`, you usually care about **logical** cores (what the scheduler can use).

---

## 2. How it chains (who calls what)

1. **Hardware** exposes logical CPUs (interrupts, timers, per-core / shared caches).
2. The **kernel** keeps **runnable** thread queues and applies a **scheduling policy** (which thread runs on which CPU and for how long — **time quantum**).
3. Each **process** has one or more **OS threads**; the kernel does **not** execute “Java lines” — it runs **machine code** (JVM native code + JIT).
4. The **JVM** runs **inside one process**; **Java platform threads** usually map **1:1** to OS threads; **virtual threads** (JDK 21+) decouple logical tasks from OS threads on I/O.

Useful sentence: *the OS schedules **kernel threads**; the JVM asks the OS for threads; your code schedules **tasks** on top of that model.*

---

## 3. What a “core” means in practice

- **Physical core:** count of real execution “engines” on the chip (e.g. 8 P-cores + 4 E-cores on a hybrid laptop — the OS still lists them as enumerable logical CPUs).
- **Logical core:** what `top`, Task Manager, or `availableProcessors()` treat as an **independent** unit for scheduling work.

**Why it matters:** for pure **CPU-bound** work, the ceiling for **parallel speedup** ties to how many instructions **actually** advance in parallel on physical cores; SMT logical CPUs help when the pipeline has bubbles (misses, I/O, mixed load).

---

## 4. What a “thread” is (do not mix them up)

| **OS thread** | **Java thread** (platform) | **Virtual thread** (Java 21+) |
|---------------|----------------------------|------------------------------|
| Entity scheduled by the kernel | `Thread` object bound to an OS thread (typical) | Lightweight continuation; blocking on I/O may release the OS thread |
| Stack and context in kernel / process | Java stack + JVM state | Logical stack managed by the runtime |
| Many created → scheduler pressure | `new Thread(...).start()` costly at scale | Thousands feasible for “one task per request” I/O-bound style |

Two Java threads in the **same process** share the **heap**; each has its **own stack** (see `basics-memory`).

---

## 5. How to “calculate” and size (rules of thumb)

### 5.1 How many processors does the JVM see?

```java
int n = Runtime.getRuntime().availableProcessors();
```

- Returns **processors available to the JVM** for **parallel work** — in practice, visible **logical** CPUs (may differ under **cgroups** in containers: sees 32 from the host but only 2 effective CPUs).

There is **no** cross-platform standard Java API for “physical cores only”; use OS tools or native libraries.

### 5.2 Pools for **CPU-bound** work

Goal: avoid **more runnable threads** than units that truly execute instructions in parallel (with small slack).

- **Common rule:** pool size ≈ **n** or **n + 1**, with `n = availableProcessors()`.
- **Why “+1”?** Sometimes one thread is doing minimal work while another is almost done — small buffer (not magic).

**More** threads than useful cores in CPU-bound → **contention** (locks), **context switches**, and **lower** throughput.

### 5.3 Pools for **I/O-bound** (blocking) work

While a thread **waits** on network/disk, a core can serve another. Hence pools **larger** than `n` — value depends on **latency**, **request rate**, and **memory** (each platform thread uses stack).

- Virtual threads (JDK 21+) change the design: many logical tasks, **few** OS threads.

### 5.4 Parallel streams / `ForkJoinPool.commonPool()`

The *common pool* defaults to **up to** `max(n-1, 1)` workers on many JVMs (implementation detail — do not hard-code in business rules). **Parallelism** ties to `availableProcessors()`, not how many tasks you submit.

### 5.5 Containers (Docker / Kubernetes)

- `availableProcessors()` may **not** match real **CPU quota**.
- Size **requests/limits** and load-test; do not assume “node cores” = “my pod’s cores”.

---

## 6. Costs that add up

- **Context switch:** switching threads → hurts cache locality, spends cycles in the kernel.
- **Contention:** many threads fighting for the same lock or memory bus.
- **Memory:** each platform thread ≈ **hundreds of KB to MB** of stack (configurable `-Xss`); adds up fast.

---

## 7. Tools to inspect the machine (outside Java)

| OS | Where to look |
|----|----------------|
| Windows | Task Manager → CPU → logical / physical cores |
| Linux | `lscpu`, `/proc/cpuinfo` |
| macOS | `sysctl -n hw.ncpu`, `sysctl hw.physicalcpu` |

Cross-check with JVM output from `ProcessorThreadExamples` / `OsRuntimeSnapshot`.

---

## 8. Where to continue in this repo

| Order | Resource |
|-------|----------|
| 1 | [processosthreadsecpu-en.md](processosthreadsecpu-en.md) — architect summary |
| 2 | [jvmeagendamento-en.md](jvmeagendamento-en.md) — JVM and virtual threads |
| 3 | `basics-memory` — per-thread stack, shared heap |
| 4 | `core` — `MultithreadingIntro`, `JvmMemoryModelIntro` |
| 5 | `java21` — virtual threads in code |

---

## 9. External references (optional)

- Oracle docs: `Runtime.availableProcessors()`
- Linux schedulers: CFS (*Completely Fair Scheduler*) — *fairness* and time quantum
- Intel / AMD: SMT documentation for real parallelism limits
