# `ObjectsConstructorsThis`

**PT:** [objectsconstructorsthis.md](objectsconstructorsthis.md)

Example: `ObjectsConstructorsThis.java` (inner class `Lampada`).

## Class and object

- **Class** (`Lampada`): blueprint / type — defines fields and methods.
- **Object** (`new Lampada("sala")`): concrete **instance** in memory; each has its own state (`ligada`, etc.).

## `new` and constructor

`new Lampada("sala")` **allocates** the object and calls the **constructor** `Lampada(String id)` to initialize fields.

## `this`

Inside the constructor, `this.id = id` means: the **`id` field of this object** receives the **parameter** `id`’s value. Without `this`, name clashes can be ambiguous in other contexts.

## `private` fields

Only code **inside the same class** accesses them directly. The “outside world” uses methods like `ligar()` and `resumo()` — this is **encapsulation** (broader OOP in `core`).
