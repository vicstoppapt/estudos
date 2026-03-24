# `StaticMembers`

**PT:** [staticmembers.md](staticmembers.md)

Example: `StaticMembers.java` (inner class `Visitante`).

## `static` field

`private static int totalCriados` — **one** counter for the **whole** class, not a copy per object. All `Visitante` instances share the same `totalCriados`.

## `static` method

`Visitante.getTotalCriados()` — called via the **class name**; no `new` needed. Inside `static` there is **no** instance `this`: no fixed “this object”.

## Instance vs class

- **`id`** (non-static): each `Visitante` has its own.
- **`totalCriados` (static):** single value, incremented in **each** constructor.

This sets the stage for `static` in `MultithreadingIntro` (`LOCK`, class variables) and concurrency in `core`.

## Caution

Too much `static` becomes hard-to-test global state; use it when the meaning is truly “of the whole class”.
