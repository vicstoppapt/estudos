# `StreamsIntro`

**English:** [streamsintro-en.md](streamsintro-en.md)

Exemplo: `StreamsIntro.java`.

## Objetivo

Pipeline **lazy** (intermediárias) até operação **terminal** que força execução; segundo trecho com **`mapToInt`** para evitar boxing na soma.

## No código

- `filter` + `map` + `collect(Collectors.toList())` — materializa nova lista (pares dobrados).
- `mapToInt(Integer::intValue).sum()` — `IntStream` e `sum()` terminal.

## Nuances

- **Uma stream, um uso:** após terminal, stream está consumida.
- **Paralelo:** `parallelStream()` exige operações associativas e cuidado com side effects.
- **Ordem:** `forEach` em paralelo não garante ordem; `forEachOrdered` custa mais.
