**PT:** [recordpatternsswitch.md](recordpatternsswitch.md)

# `RecordPatternsSwitch`

Example: `RecordPatternsSwitch.java`.

## Goal

**Record pattern** in `switch`: `case Ponto(int x, int y)` decomposes components directly.

## Nuances

- Requires the selector to be compatible with the pattern; `default` covers non-`Ponto`.
- Combine with `when` guards in other examples (Java 21).
