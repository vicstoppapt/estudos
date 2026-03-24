**PT:** [switchexpressions.md](switchexpressions.md)

# `SwitchExpressions`

Example: `SwitchExpressions.java`.

## Goal

**`switch` as an expression** (value): arrow `->` or a block with **`yield`** when there are multiple statements.

## In the code

- Multiple labels `case 1, 7`.
- Block `{ yield "dia util"; }` yields the expression value.

## Nuances

- **`break`/`yield`:** in an expression, `yield` returns a value; do not mix carelessly with the old style.
- **Exhaustiveness:** with enum/sealed, the compiler may require full coverage.
