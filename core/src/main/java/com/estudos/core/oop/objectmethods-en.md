**PT:** [objectmethods.md](objectmethods.md)

# `ObjectMethods`

Example in: `ObjectMethods.java` (nested class `Ponto`).

## Goal

Show a coherent **`toString` / `equals` / `hashCode` trio** for a simple immutable value (`x`, `y`).

## `toString`

- Overridden for **readable**, stable debug output (`Ponto(1,2)`).
- Without it, `println` would show class name + hexadecimal `@hash` with little information.

## `equals(Object o)`

- **Fast reflexivity:** `this == o` → `true` (same reference).
- **Type:** if not `instanceof Ponto`, return `false` — avoids `ClassCastException` and respects symmetry with other types.
- **Fields:** compare `x` and `y` — **value** equality for two distinct instances on the heap.

## `hashCode`

- Implemented with **`Objects.hash(x, y)`** — must use **the same fields** as `equals`.
- **Contract:** if `a.equals(b)`, then `a.hashCode() == b.hashCode()`. The converse need not hold (collisions are possible), but sound `equals` + aligned `hashCode` avoids bugs in `HashMap`/`HashSet`.

## What `main` demonstrates

- Two distinct **`new Ponto(1,2)`** instances: **`equals` true** despite different references.
- `toString` reflects the value.

## Nuance

For types with `double` or approximate comparison, `equals` may need another policy; for `int`, bitwise equality is expected.
