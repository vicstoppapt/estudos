[PT](optionalenhancements.md)

# `OptionalEnhancements`

Example: `OptionalEnhancements.java`.

## Goal

**`orElseThrow()`** with no arguments (since Java 10): throws **`NoSuchElementException`** with the default message when empty — useful when empty is a logic bug.

## Nuances

- The version with `Supplier<? extends Throwable>` remains available for domain exceptions.
- Do not overuse: if empty is normal flow, prefer `orElse` / `orElseGet`.
