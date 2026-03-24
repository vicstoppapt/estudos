**PT:** [challengethreadsafecounter.md](challengethreadsafecounter.md)

# `ChallengeThreadSafeCounter`

Solution in: `ChallengeThreadSafeCounter.java`. Tests verify 100_000 after two threads.

## Goal

Shared counter with **`AtomicInteger`**: many concurrent increments **without** explicit `synchronized` on the public API.

## Why it works

- `incrementAndGet()` combines read-increment-write into a single **atomic** operation on the hardware (typical CAS).
- Avoids *lost update* that would happen with `int` and `++` without a lock.

## Nuances

- **Scope:** great for simple metrics; composite structures (e.g. a map) need other primitives.
- Alternative in the main project: `MultithreadingIntro` with `synchronized` + `int`.

## Load in the example

Two threads × **50_000** increments → **100_000** expected.
