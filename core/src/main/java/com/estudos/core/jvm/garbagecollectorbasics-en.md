**PT:** [garbagecollectorbasics.md](garbagecollectorbasics.md)

# `GarbageCollectorBasics`

Example in: `GarbageCollectorBasics.java`.

## Goal

Three ideas: **approximate memory** via `Runtime`, **allocation** visible as increased usage, and **dropping references** to make an object **eligible** for collection — without promising **when** the GC runs.

## The code line by line (conceptual)

- **`Runtime.getRuntime()`** — application singleton for coarse memory queries.
- **`totalMemory() - freeMemory()`** — **approximate** “used”; the JVM may grow/shrink heap and “free” is not a lab-precise meter.
- **`new byte[1024 * 1024]`** — allocates a large array; “used” often **rises** between before/after snapshots (not guaranteed at every moment due to concurrent GC or internal tuning, but typical in a teaching example).
- **`blob = null`** — removes the example’s only strong reference to the array; the object becomes **eligible** for GC.
- The final print reminds: **no timing guarantee** for collection; **`System.gc()`** is only a hint and should not drive design.

## Nuances

- **Finalize:** avoid `finalize` for resource cleanup; prefer `try-with-resources` / `Cleaner` where applicable.
- **Generations / collectors:** since JDK 9+ the default evolved (G1, then others by version). The example does not pin an algorithm — only **reachability**.
- **Real profiling:** for serious diagnosis, use **JFR**, **VisualVM**, flags `-Xlog:gc`, etc.

## Common misinterpretation

Difference between “memory returned to the OS” and “object was collected” — the JVM may keep reserved regions; what matters for leaks is **accidental references** keeping objects alive.
