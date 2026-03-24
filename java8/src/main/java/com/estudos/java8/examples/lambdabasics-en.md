[PT](lambdabasics.md)

# `LambdaBasics`

Example: `LambdaBasics.java`.

## Goal

**Lambda** syntax as implementation of an interface with a **single abstract method** (`@FunctionalInterface`) and use in **`forEach`** (`Consumer`).

## Nuances

- The lambda `(a, b) -> a + b` implements `IntOp.apply` — parameter types inferred from the target interface.
- `forEach(n -> ...)` — `Consumer<? super T>`; typical side effect (here `println` with `toUpperCase`).
- Lambdas capture **effectively final** variables from the enclosing scope.

## Relation

Ready-made interfaces in `java.util.function`: see `functionalinterfaces-en.md`. Short references: `methodreferences-en.md`.
