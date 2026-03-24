**PT:** [patternmatchinginstanceof.md](patternmatchinginstanceof.md)

# `PatternMatchingInstanceof`

Example: `PatternMatchingInstanceof.java`.

## Goal

**`instanceof` with binding:** `if (o instanceof String s && s.length() > 2)` — `s` is typed and in scope in the block.

## Nuances

- The part after `&&` is evaluated only if `instanceof` is true (**short-circuit**).
- Avoids noisy manual casts.
- Evolves into **pattern matching in `switch`** in Java 21 (another module).
