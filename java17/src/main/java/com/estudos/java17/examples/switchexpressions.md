# `SwitchExpressions`

**English:** [switchexpressions-en.md](switchexpressions-en.md)

Exemplo: `SwitchExpressions.java`.

## Objetivo

**`switch` como expressão** (valor): setas `->` ou bloco com **`yield`** quando há múltiplas instruções.

## No código

- Múltiplos labels `case 1, 7`.
- Bloco `{ yield "dia util"; }` produz valor da expressão.

## Nuances

- **`break`/`yield`:** em expressão, `yield` devolve valor; não misturar com estilo antigo sem cuidado.
- **Exhaustiveness:** com enum/sealed, o compilador pode exigir cobertura completa.
