[PT](streamsintro.md)

# `StreamsIntro`

Example: `StreamsIntro.java`.

## Goal

**Lazy** pipeline (intermediate ops) until a **terminal** operation forces execution; second segment uses **`mapToInt`** to avoid boxing in the sum.

## In the code

- `filter` + `map` + `collect(Collectors.toList())` — materializes a new list (doubled evens).
- `mapToInt(Integer::intValue).sum()` — `IntStream` and terminal `sum()`.

## Nuances

- **One stream, one use:** after a terminal op, the stream is consumed.
- **Parallel:** `parallelStream()` requires associative operations and care with side effects.
- **Order:** `forEach` in parallel does not guarantee order; `forEachOrdered` costs more.
