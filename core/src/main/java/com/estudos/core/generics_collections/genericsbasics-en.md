**PT:** [genericsbasics.md](genericsbasics.md)

# `GenericsBasics`

Example in: `GenericsBasics.java`.

## Goal

Show two ideas that often confuse beginners: **unbounded wildcard** (`List<?>`) and **invariance** of generic types (`List<String>` is not a subtype of `List<Object>`).

## What the code demonstrates

- **`imprimirLista(List<?> lista)`** — the parameter is “a list of some unknown type”. It is only safe to treat each element as **`Object`**: iterating and printing works; you cannot **add** a `String` (or another concrete type) without breaking the original list’s guarantee, so the `List<?>` API restricts certain operations at compile time.
- **`main`** creates `List<String>` and passes it to `imprimirLista` — this **compiles** because `List<String>` is compatible with `List<?>` in a **consumer** position (read-only of what is already in the list, in the sense of this example).
- The commented line **`List<Object> objs = strings;`** — does **not** compile. Even though every `String` is an `Object`, `List<String>` is **not** a subtype of `List<Object>`: generics in Java are **invariant** for the whole list type. Otherwise someone could do `objs.add(new Object())` and break a list that was only `String`.

## Nuances

- **Erasure:** in bytecode, it usually becomes `List` + `Object` where needed; the compiler enforces types in source. That is why it sometimes feels “just a warning”, but type safety exists at compile time.
- **`List<?>` vs `List<Object>`:** `List<?>` means “list of unknown type”; `List<Object>` means “list where the API expects to work with `Object`” — they are not interchangeable with `List<String>`.
- **PECS** (Producer Extends, Consumer Super) shows up in more advanced APIs; this example is the step before: understand `?` and invariance.

## Suggested reading

JLS and official tutorials on wildcards and subtyping of parameterized types.
