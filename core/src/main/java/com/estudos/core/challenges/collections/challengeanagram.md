# `ChallengeAnagram`

Solução em: `ChallengeAnagram.java`. Testes: `ChallengeAnagramTest`.

## Objetivo

Decidir se duas strings são **anagramas** considerando apenas **letras** (ignora espaços e pontuação), **case-insensitive**.

## Algoritmo

1. Extrair code points, filtrar `Character.isLetter`, normalizar com `toLowerCase`.
2. Colocar em `char[]`, `Arrays.sort` em cada lado.
3. `Arrays.equals` — mesma multiset de letras → anagrama.

## Nuances

- **Unicode:** uso de `codePoints()` evita quebrar surrogate pairs ao iterar “caractere lógico”.
- **Complexidade:** O(k log k) com k = número de letras; alternativa é contar frequências em mapa O(k).
- **Null:** ambos `null` → `false` na implementação atual (defesa explícita).

## Estudo

Compare com contagem em `int[26]` só para ASCII vs abordagem Unicode-safe.
