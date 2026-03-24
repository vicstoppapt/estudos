[PT](methodreferences.md)

# `MethodReferences`

Example: `MethodReferences.java`.

## Goal

`Type::method` forms when the lambda only **delegates** to an existing method.

## In the code

- **`String::toUpperCase`** in `map`: **instance** reference with implicit receiver (each stream element becomes `this`).
- **`StringBuilder::new`** with `map` from `Stream<String>`: the compiler picks the `StringBuilder(String)` constructor.

## Nuances

Four classic categories: static (`Math::abs`), bound instance, unbound instance on arbitrary type (`String::length`), constructor (`ArrayList::new`). The signature must match the expected **functional type**.
