**PT:** [recordsexample.md](recordsexample.md)

# `RecordsExample`

Example: `RecordsExample.java`.

## Goal

**Record** (Java 16+): immutable type with state in the header; the compiler generates the canonical constructor, accessors, `equals`/`hashCode`/`toString`.

## Nuances

- Fields are implicitly `private final`; accessors are `name()` without a `get` prefix.
- You can add methods, compact constructors for validation, implemented interfaces.
- Not “just a DTO”: can hold invariants and behavior.
