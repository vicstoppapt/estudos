# `ComparableComparator`

Exemplo em: `ComparableComparator.java` (`record Item`).

## Objetivo

Separar **ordem natural** do tipo (`Comparable<T>`) de **ordens ad hoc** (`Comparator<T>`) sem alterar a classe/tipo permanentemente.

## `Item` e `Comparable<Item>`

- **`compareTo`** implementa ordem **padrão** do tipo: aqui **preço crescente** (`Integer.compare(this.preco, o.preco)`).
- **Semântica de retorno:** negativo/zero/positivo como contrato de `Comparable` (igual ao `Comparator`).

## `itens.sort(null)`

- **`null`** como `Comparator` em `List.sort` significa: usar a **ordem natural** dos elementos — ou seja, delegar a **`Comparable`** de `Item`.
- Resultado: ordenação por **preço** (`10` antes de `20`).

## `itens.sort(Comparator.comparing(Item::nome))`

- **Nova regra só para esta chamada:** ordenar por **`nome`** lexicograficamente (`String` natural order).
- **Não remove** a capacidade de `Comparable`; apenas **sobrescreve** a ordenação desta operação.
- Depois desta linha, a lista fica ordenada por nome; o exemplo imprime as duas etapas para comparar.

## Nuances

- **Estabilidade:** `TimSort` (usado em `List.sort`) é **estável**: empates preservam ordem relativa anterior — útil quando se encadeiam ordenações por critérios secundários (`thenComparing`).
- **Nulls:** `Comparator.nullsFirst` / `nullsLast` quando elementos podem ser `null`.
- **Records:** geram accessors `nome()`, `preco()` — por isso `Item::nome` funciona como extrator.

## Extensão mental

Para ordenar por preço e desempate por nome: `Comparator.comparing(Item::preco).thenComparing(Item::nome)`.
