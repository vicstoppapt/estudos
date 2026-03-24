**PT:** [stringimmutability.md](stringimmutability.md)

# `StringImmutability`

Example in: `StringImmutability.java`.

## Goal

Separate three notions: **`String` instance immutability**, **`==` (reference identity)** vs **`equals` (content)**, and the **string pool** (literal interning).

## What each print illustrates

### Literals `a` and `b` both `"java"`

- The compiler may make **equal literals** point to the **same instance** in the pool.
- Therefore **`a == b`** is often **`true`** — not a universal guarantee of “equal content” in every `String` construction scenario, but the typical case for identical literals.

### `c = new String("java")`

- **`new` always creates another object** on the heap (content may match the literal).
- **`a == c`** tends to be **`false`**: different references.
- **`a.equals(c)`** is **`true`**: content comparison.

### `c.intern()`

- **`intern()`** returns the pool’s **canonical** reference for that character sequence.
- After `intern()`, **`a == c.intern()`** may be **`true`** because both references point to the pool entry.

## Concatenation `s = s + " world"`

- `String` is **immutable**: `s + " world"` produces a **new** `String`; variable `s` then refers to that new instance.
- The previous `"hello"`, if nothing else references it, becomes **eligible for GC** (not an in-place modification).

## Nuances

- **`==` on `String`:** almost always wrong for business logic; use **`equals`** (and `equalsIgnoreCase` when appropriate).
- **Pool:** do not rely on `==` for strings from I/O, database, or `new` without `intern()` — use `equals`.
- **Performance:** concatenation in a loop with `+` can create many intermediate instances; for heavy building, `StringBuilder` is more appropriate (another example/package).
