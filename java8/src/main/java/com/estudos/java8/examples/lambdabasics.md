# `LambdaBasics`

**English:** [lambdabasics-en.md](lambdabasics-en.md)

Exemplo: `LambdaBasics.java`.

## Objetivo

Sintaxe de **lambda** como implementação de interface com **um único método abstrato** (`@FunctionalInterface`) e uso em **`forEach`** (`Consumer`).

## Nuances

- A lambda `(a, b) -> a + b` implementa `IntOp.apply` — tipos dos parâmetros inferidos pela interface alvo.
- `forEach(n -> ...)` — `Consumer<? super T>`; efeito colateral típico (aqui `println` com `toUpperCase`).
- Lambdas capturam variáveis **efetivamente finais** do escopo envolvente.

## Relação

Interfaces prontas em `java.util.function`: ver `functionalinterfaces.md`. Referências curtas: `methodreferences.md`.
