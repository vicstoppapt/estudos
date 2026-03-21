# `ChallengeStreamWordCount`

Solução: `ChallengeStreamWordCount.java`. Testes em `src/test`.

## Objetivo

Contar ocorrências de **palavras** (tokens separados por whitespace), **case-insensitive**, com **`Collectors.groupingBy`** + **`counting`**.

## Nuances

- `split("\\s+")` e `trim` tratam string vazia / só espaços → mapa vazio.
- `filter` remove tokens vazios defensivos.
- Alternativa: `Collectors.toMap` com merge para contagem.
