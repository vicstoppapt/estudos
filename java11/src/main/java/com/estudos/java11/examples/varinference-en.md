[PT](varinference.md)

# `VarInference`

Example: `VarInference.java`.

## Goal

**Local type inference** with `var`: type is inferred from the **initializer**; useful for long names and for `new` with an explicit generic type on the right-hand side.

## Limitations (do not use `var` when it hurts readability)

- No initializer on the same line → does not compile.
- Not on method parameters, not on field types (up to current Java), not when the inferred type is too ambiguous for humans.
- **`var` is not** “dynamic typing” — still strongly typed at compile time.

## In the code

`var lista` is `ArrayList<String>`; `for (var s : lista)` infers element as `String`. `List.of` returns an **immutable** list (Java 9+).
