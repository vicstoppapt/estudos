# `EqualsHashCodeContract`

**English:** [equalshashcodecontract-en.md](equalshashcodecontract-en.md)

Exemplo em: `EqualsHashCodeContract.java` (classe interna `Pessoa`).

## Objetivo

Mostrar por que **`equals` e `hashCode` juntos** são obrigatórios para chaves de **`HashMap`** (e elementos de **`HashSet`**): localizar o bucket e depois **disambiguar colisões** com `equals`.

## Fluxo no `HashMap`

1. **`put(p, "dev")`** — calcula **índice/bucket** a partir de `hashCode()` de `p` (após mistura interna da JVM).
2. **`get(p)`** — mesma chave **referencial** → mesmo hash → mesmo bucket → `equals` confirma → devolve `"dev"`.
3. **`get(new Pessoa("Ana", 1))`** — **outro objeto** no heap:
   - Se **`hashCode`** for igual ao da chave original **e** **`equals`** retornar `true`, o mapa encontra a entrada.
   - Se **`hashCode`** divergir (bug comum ao sobrescrever só `equals`), o `get` pode ir para o bucket errado e retornar **`null`** mesmo com “mesmo conteúdo”.

## O que `Pessoa` faz certo

- `equals` usa **`id` e `nome`** (com `Objects.equals` para `String`).
- `hashCode` usa **`Objects.hash(nome, id)`** — mesmos campos.

## Anti-padrões (não estão no código, mas o exemplo existe para evitá-los)

- **`equals` baseado em campos mutáveis** — se a chave muda depois do `put`, o mapa “perde” a entrada.
- **Subclasse que redefine `equals` sem cuidado com simetria** — Liskov em coleções é delicado; preferir composição ou documentação clara.

## Relação com `ObjectMethods`

Lá o foco é a tríade em um tipo valor; aqui o foco é **efeito prático em `HashMap`**.
