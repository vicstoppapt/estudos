# CPU cache, browser cache, RAM, and JVM

**PT:** [cpucachejvmenavegador.md](cpucachejvmenavegador.md)

Cross-cutting text (no dedicated `.java`). Overlaps with [memoryphysicaldeepdive-en.md](memoryphysicaldeepdive-en.md) (physical hierarchy) and [memoryandreferences-en.md](memoryandreferences-en.md) (Java model).

---

## 1. Two caches with the same name — similar idea?

**Only in the general concept:** store a **copy** of data that already came from a **slower** place so next time you **do not repeat** that path.

| | **CPU cache (L1 / L2 / L3)** | **Browser cache** |
|---|------------------------------|-------------------|
| **What** | Copies of **memory lines** from RAM (byte blocks: machine code + data) | Copies of **HTTP resources** (HTML, CSS, JS, images, …) |
| **Where** | **On the chip** (very fast SRAM) | **Disk** (and sometimes RAM), on your PC, managed by the **browser** |
| **Who manages** | CPU **hardware** — transparent to the programmer | **Software** (browser + headers like `Cache-Control`) |
| **Why** | Avoid going to **RAM** so often (latency in **nanoseconds**) | Avoid going to the **network** again (latency in **ms** or worse) |
| **Survives power-off** | **No** (volatile) | Often **yes** (files on disk until expiry or you clear) |

### Etymology

**Cache** comes from French *cacher* (to hide). In computing, it is **auxiliary** storage “under” the main path — fast to consult.

---

## 2. “Processor available memory” — do not confuse three things

People often mix:

1. **CPU cache** — small, **fixed per CPU model**, managed only by hardware.  
2. **RAM** — what you install on the motherboard (GB).  
3. **JVM heap** (or `java` process memory) — what `-Xmx` and the OS cap.

The **“low”** number when talking about cache **is normal**: L1 in **tens of KB** per core, L2 in **hundreds of KB**, L3 **several MB** total. Compared with **8–32 GB RAM**, it looks tiny — but cache is **expensive SRAM** next to the core; it **must** be small to stay fast.

---

## 3. How to “know” how much cache you have

- **L1/L2/L3 sizes:** **CPU model** specs (Intel/AMD site, tools like CPU-Z, machine docs). These are **hardware** values, not something you “add” like RAM.
- **There is no** (for a normal app) “free cache in GB” like RAM. The CPU **does not expose** “reserve 2 MB of L3 for me”; hardware **evicts** and **loads** lines on its own.
- If you want **memory available to your Java app**, look at **process RAM** and **heap** (`-Xmx`, VisualVM, `Runtime`, task manager) — **that is not** “CPU cache size”.

---

## 4. What the CPU cache holds (not “a Java variable”)

The processor does **not** think in `int x` or “whole method” like the language.

It works with **cache lines** (typically **64 bytes**): copies of **memory regions** (machine instructions, object data, stack, etc.) that live in **RAM**.

- **What goes in/out** is decided by **hardware policy** (temporal and spatial locality).  
- It is **opaque**: there is no Java API for “this variable goes to L1”.

There is **other processing** in the sense of dedicated circuits (cache controller, coherence across cores), not “the same thing” as hitting RAM at the same latency.

---

## 5. Can you force “critical” data into cache via JVM?

(What **“no API”** means here: see [apiconceitogeral-en.md](../../../../../../../../basics-language/src/main/java/com/estudos/basics/apiconceitogeral-en.md).)

**In practice, no.**

- The JVM **does not offer** “pin in L1/L2” nor normal `-XX:` flags for that.  
- You **do not** directly control what hardware puts in cache.

What **indirectly** influences *cache hits* (and performance):

- **Locality:** accessing **contiguous** data in sequence (e.g. arrays) tends to use lines better than random jumps in memory.  
- **Hot** code in tight loops tends to have instructions reused in the **instruction** cache.

This is **micro-optimization**; in Java you still have **GC**, mutable **layout**, **JIT** — control is much more indirect than C on embedded systems. Scenarios with **cache locking** or hard real-time rules are **outside** typical Java application use.

---

## 6. Who “thinks” about this: motherboard or CPU? Who “programs” what sits in cache?

### 6.1 Where the decision is made (hardware)

- The **CPU vendor** (Intel, AMD, Apple M-series, ARM licensees, etc.) **designs the chip**: L1/L2/L3 sizes, **cache controllers**, **coherence** across cores (protocol families like MESI), speculative **prefetch**, **line replacement** algorithms (real implementations are often LRU approximations or PLRU trees — **microarchitecture**, often **not** fully documented publicly).
- The **motherboard** provides **socket**, **power**, **traces to RAM** (DDR), M.2/SATA slots, etc. It does **not** “decide” which addresses enter L1: that is **logic inside the CPU package** (or SoC die).

### 6.2 In practice, who “programs” what should or should not be stored?

**Nobody writes an application program that says “put this variable in L1”.**

What exists is **fixed hardware behavior** + **reaction to accesses**:

1. Your code (compiled to instructions) **reads/writes** memory addresses.
2. On each access, the CPU’s **memory management / cache hierarchy**, in **hardware**:
   - **hits** if the line is already in cache;
   - **misses** and **fetches** from RAM (and upper levels) the corresponding **line**;
   - when cache is full, **evicts** some line per **silicon policy** (not the OS choosing byte by byte).

So: **there is logic**, but it is **microarchitecture** — circuits and algorithms **inside the CPU**, not a config file the Java programmer edits.

### 6.3 Role of OS, firmware, and “programming”

- **Firmware (BIOS/UEFI)** and **OS** can influence **indirect** things (power, *boost*, CPU affinity, in very special cases CPU registers — MSRs — in **kernels** or **hypervisors**). This is **not** day-to-day “decide L1 contents per variable”.
- **Compiler / programmer** influence **access patterns** (locality), and **hardware reacts** — but there is **no** stable API like `cache.put(x)`.

### 6.4 Short interview answer

- **Who designs the cache?** → **CPU manufacturer** (and SoC design for integrated chips).  
- **The motherboard?** → Connects CPU to RAM and peripherals; **does not** replace internal CPU cache logic.  
- **Who programs the contents?** → **Nobody in application software**; it is **automatic in hardware** based on **memory accesses**.

---

## 7. Where to see “how much is available” for the application

| Need | Where to look |
|------|----------------|
| OS / process RAM | Task Manager (Windows), `htop` (Linux), etc. |
| Java heap | `-Xmx`, JVM tools, `Runtime.getRuntime().freeMemory()` (rough) |
| Fine profiling | JFR, VisualVM, async-profiler (includes cache *miss* events in some modes — advanced) |

**CPU cache** remains an **accelerator** underneath; it **does not replace** “application available memory”.

---

## 8. Summary

1. **Browser cache** and **CPU cache** share the **idea** of a fast copy; **layer**, **size**, and **management** differ completely.  
2. **CPU cache** is **small by design**; what goes there are **memory blocks**, not “methods” as a language concept.  
3. You **cannot** force data into L1 via normal Java/JVM code; improve **access patterns** if you need extreme performance (and measure with a profiler).  
4. For “how much memory for my app”, think **RAM + JVM heap**, not “GB of cache”.  
5. **Who defines the cache** is the **CPU manufacturer**; **contents** are managed by **hardware** from **accesses** — not by an application “program”.

---

## 9. Related reading in this repo

- [memoryphysicaldeepdive-en.md](memoryphysicaldeepdive-en.md) — physics, swap, latencies.  
- [memoryandreferences-en.md](memoryandreferences-en.md) — stack, heap, references.  
- `basics-os-concurrency` → [processosthreadsecpu.md](../../../../../../../../basics-os-concurrency/src/main/java/com/estudos/basics/os/processosthreadsecpu.md) — cores, OS threads, scheduling.  
- `core` → `jvmmemorymodelintro.md`, `GarbageCollectorBasics.java`.
