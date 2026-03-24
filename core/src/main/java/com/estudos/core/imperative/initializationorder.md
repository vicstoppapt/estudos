# `InitializationOrder`

**English:** [initializationorder-en.md](initializationorder-en.md)

Exemplo em: `InitializationOrder.java` (inclui classe interna estática `Sub`).

## Objetivo

Mostrar a **ordem real** de execução de **blocos estáticos**, **blocos de instância** e **construtores** quando há **hierarquia** e **primeiro uso** de classe.

## Regras resumidas (modelo mental)

1. **Carregamento de classe** — antes de qualquer `new` ou uso estático da classe, a JVM carrega a classe. Blocos **`static { }`** rodam **uma vez** por carregamento, na ordem definida pela linguagem (superclasse antes de subclasse na cadeia de carregamento/inicialização estática).
2. **Instância** — ao executar `new Sub()`:
   - Construtor da subclasse **chama implicitamente** o construtor da super (`super()`), exceto se houver `super(args)` explícito.
   - **Antes** do corpo do construtor da super rodam os **blocos de instância** da super e depois o construtor da super.
   - Depois blocos de instância da sub e construtor da sub.

## O que a numeração dos prints representa

- **`1`** — bloco estático de `InitializationOrder` quando essa classe é carregada (antes de `main` completar o que precisa da classe).
- **`2`** — início do `main`.
- **`3`** — bloco estático de `Sub` na primeira vez que `Sub` é usada (aqui, no `new Sub()`).
- **`4`, `5`** — bloco de instância e construtor da **super** durante construção da instância de `Sub`.
- **`6`, `7`** — bloco de instância e construtor de **`Sub`**.

A ordem exata que você vê na saída **confirma** a regra “super antes da sub” na construção de instância e o momento em que os estáticos da `Sub` rodam (quando a classe `Sub` é inicializada).

## Nuances

- **Ordem de classes no `main`:** se você referenciasse só `Sub` primeiro, os estáticos ainda respeitam a hierarquia (superclasse inicializada antes no processo de linking da sub — detalhe fino depende de quando cada classe é carregada; o exemplo atual foi desenhado para bater com os prints comentados no código).
- **Campos vs blocos:** inicializadores de campo e blocos de instância rodam na ordem **textual** na classe, antes do construtor.
- **Não confundir** com ordem de **múltiplas classes** sem relação de herança — cada árvore segue suas próprias regras.

Para precisão formal, cruzar com JLS (inicialização de classes e criação de instâncias).
