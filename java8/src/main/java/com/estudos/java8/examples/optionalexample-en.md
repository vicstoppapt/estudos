[PT](optionalexample.md)

# `OptionalExample`

Example: `OptionalExample.java`.

## Goal

Represent **absence** of a value without `null` in the lookup API signature; `orElse` and `ifPresent`.

## Nuances

- **`Optional` is not** a replacement for every null in fields — typical use is **method return**.
- **`Optional.of(null)`** throws; use `ofNullable`.
- **`orElseGet(Supplier)`** avoids evaluating an expensive fallback when a value is present.
- Do not serialize `Optional` in DTOs without a good reason.

## Anti-pattern

`Optional` as a method parameter overuse — generally prefer overloads or a clear contract.
