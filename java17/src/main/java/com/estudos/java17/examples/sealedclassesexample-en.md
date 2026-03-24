**PT:** [sealedclassesexample.md](sealedclassesexample.md)

# `SealedClassesExample`

Example: `SealedClassesExample.java`.

## Goal

**Sealed types** (`sealed` / `permits`): hierarchy closed at compile time — only `Lit` and `Add` implement `Expr` in this package.

## In the code

`eval` uses `instanceof` with a pattern variable; expression tree with records.

## Nuances

- Lets the compiler reason about **exhaustiveness** in `switch` in later versions (Java 21+).
- `permits` can be omitted in the same file under certain conditions; here it is explicit.
