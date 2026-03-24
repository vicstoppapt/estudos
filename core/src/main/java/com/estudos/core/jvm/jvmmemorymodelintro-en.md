**PT:** [jvmmemorymodelintro.md](jvmmemorymodelintro.md)

# `JvmMemoryModelIntro`

Example in: `JvmMemoryModelIntro.java`.

## Goal

**Vocabulary** anchor: where data lives in the JVM in a simplified model (**stack**, **heap**) and a pointer to **concurrency** (**memory model**, *happens-before*).

## What the program does

It does not instrument the JVM or read MXBeans: it only prints a **text block** with guide sentences. The value is in **cross-checking** with theory and then with examples that show race conditions or `volatile`.

## Stack (per thread)

- Each **thread** has its **stack of frames** (method activation).
- In general: **local** primitives and **local references** (pointers to objects) live in the method frame — not shared across threads.
- When the method returns, the frame disappears; local references stop being GC roots for that path (if no other path reaches the object).

## Heap (shared)

- **Objects** (instances) and **arrays** live on the heap; **instance fields** are inside the object.
- **`static` fields** belong to the class (metaspace / class representation in a modern JVM — implementation detail); what matters is they are **global state** reachable by all threads with the same loaded class.

## Metaspace / class loading (mentioned in the general README)

- Class code, metadata — not this `main`’s focus, but completes the map: not everything is “just heap”.

## JIT

- The JVM may **compile** bytecode to native after warm-up. Observable behavior can change (optimizations), but **language semantics** stay; for concurrency, trust JLS/JMM, not “luck”.

## Next study step

- **JLS chapter 17** — ordering, visibility, *happens-before*.
- Package `concurrency` in this project: [../concurrency/README-en.md](../concurrency/README-en.md) for threads and synchronization in practice.
