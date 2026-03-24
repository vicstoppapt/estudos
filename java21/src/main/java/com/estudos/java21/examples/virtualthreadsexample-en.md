**PT:** [virtualthreadsexample.md](virtualthreadsexample.md)

# `VirtualThreadsExample`

Example: `VirtualThreadsExample.java`.

## Goal

**Virtual threads** (Java 21): `Executors.newVirtualThreadPerTaskExecutor()` — many blocking tasks without one OS thread per task.

## Nuances

- **Not** “faster” for pure CPU; shines on massive blocking **I/O**.
- **Pinning:** `synchronized` inside a VT can limit scheduling — see JDK docs for evolution and best practices.
- `try-with-resources` closes the executor at the end.
