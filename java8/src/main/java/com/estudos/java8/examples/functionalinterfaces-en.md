[PT](functionalinterfaces.md)

# `FunctionalInterfaces`

Example: `FunctionalInterfaces.java`.

## Goal

Four shapes used constantly in the JDK 8 functional API:

| Type | Method | Role |
|------|--------|--------|
| `Predicate<T>` | `test` | boolean, filter |
| `Function<T,R>` | `apply` | transform T→R |
| `Consumer<T>` | `accept` | consumes T, void |
| `Supplier<T>` | `get` | produces T with no argument |

## Nuances

- `String::length` is a **method reference** equivalent to `s -> s.length()` where the compiler fits it into `Function`.
- `Math::random` fits `Supplier<Double>` (boxed return).
- Composition: `Predicate.and`, `Function.andThen`, etc., for reusable pipelines.
