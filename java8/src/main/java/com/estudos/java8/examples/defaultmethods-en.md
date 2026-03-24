[PT](defaultmethods.md)

# `DefaultMethods`

Example: `DefaultMethods.java`.

## Goal

Evolve an **interface** without breaking old implementations: **`default`** method with a body; **`static`** method on the interface (not overridden by implementers).

## Nuances

- **Diamond problem:** two interfaces with the same default — the concrete class must resolve with `Interface.super.method()`.
- `default` does not replace `Object` methods in a dangerous way; the language has rules for this.
- `static` on interface: call `Logger.header()` — does not go through instance polymorphism.

## Real-world use

`Iterable.forEach`, `Comparator` with defaults, etc.
