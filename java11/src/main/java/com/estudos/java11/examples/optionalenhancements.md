# `OptionalEnhancements`

**English:** [optionalenhancements-en.md](optionalenhancements-en.md)

Exemplo: `OptionalEnhancements.java`.

## Objetivo

**`orElseThrow()`** sem argumentos (desde Java 10): lança **`NoSuchElementException`** com mensagem padrão quando vazio — útil quando vazio é bug lógico.

## Nuances

- Versão com `Supplier<? extends Throwable>` continua disponível para exceções de domínio.
- Não abuse: se o vazio for fluxo normal, prefira `orElse` / `orElseGet`.
