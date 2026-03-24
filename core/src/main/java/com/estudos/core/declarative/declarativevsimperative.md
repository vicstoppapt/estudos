# `DeclarativeVsImperative`

**English:** [declarativevsimperative-en.md](declarativevsimperative-en.md)

Exemplo em: `DeclarativeVsImperative.java`.

## Objetivo

Comparar o **mesmo resultado** com laço **imperativo** (`for` + acumulador) e com **pipeline declarativo** (`stream` + operações intermediárias + terminal).

## Comportamento comum

Ambos somam apenas inteiros **pares** e **ignoram `null`** na lista (`Arrays.asList` permite `null` aqui só para exercício defensivo).

## Versão imperativa

- Variável `total` mutável, iteração explícita, condição `n != null && n % 2 == 0`.
- Fácil de depurar passo a passo no debugger.

## Versão declarativa

- `filter` aplica o mesmo predicado; `mapToInt(Integer::intValue)` evita boxing na soma; `sum()` é terminal e força execução do pipeline.
- **Lazy:** nada acontece até o terminal (`sum`).

## Nuances

- **Performance:** para listas minúsculas irrelevante; para grandes fluxos, streams podem ter overhead ou ganhar com paralelização (`parallelStream`) — com cuidado e perfil.
- **Legibilidade:** time costuma preferir streams para transformações encadeadas; laços para lógica muito ramificada.
- Pré-requisito: conforto com lambdas — ver módulo **java8**.

## `meuPlayground()`

Troque o predicado (ex.: ímpares), use `reduce`, ou outra coleção.
