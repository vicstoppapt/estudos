# `RecordPatternsSwitch`

Exemplo: `RecordPatternsSwitch.java`.

## Objetivo

**Record pattern** no `switch`: `case Ponto(int x, int y)` decompõe componentes diretamente.

## Nuances

- Exige que o seletor seja compatível com o pattern; `default` cobre não-`Ponto`.
- Combinar com guards `when` em outros exemplos (Java 21).
