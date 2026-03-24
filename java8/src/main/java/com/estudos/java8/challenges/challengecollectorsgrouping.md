# `ChallengeCollectorsGrouping`

**English:** [challengecollectorsgrouping-en.md](challengecollectorsgrouping-en.md)

Solução: `ChallengeCollectorsGrouping.java`.

## Objetivo

Agrupar strings por **chave derivada** — aqui **`String::length`** — produzindo `Map<Integer, List<String>>`.

## Nuances

- **`groupingBy`** com downstream padrão é `toList()`; outros downstreams: `counting`, `mapping`, `maxBy`.
- Ordem das listas no mapa não é ordenada a menos que use `TreeMap` ou pós-processamento.
