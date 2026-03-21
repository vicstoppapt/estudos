# `StringImmutability`

Exemplo em: `StringImmutability.java`.

## Objetivo

Separar três noções: **imutabilidade da instância `String`**, operador **`==` (identidade de referência)** vs **`equals` (conteúdo)**, e o **string pool** (interning de literais).

## O que cada print ilustra

### Literais `a` e `b` ambos `"java"`

- O compilador pode fazer com que **literais iguais** apontem para a **mesma instância** no pool.
- Por isso **`a == b`** costuma ser **`true`** — não é garantia universal de “conteúdo igual” em todos os cenários de construção de `String`, mas é o caso típico para literais idênticos.

### `c = new String("java")`

- **`new` sempre cria outro objeto** no heap (conteúdo pode ser igual ao literal).
- **`a == c`** tende a ser **`false`**: referências diferentes.
- **`a.equals(c)`** é **`true`**: comparação de conteúdo.

### `c.intern()`

- **`intern()`** devolve a referência **canônica** do pool para essa sequência de caracteres.
- Depois de `intern()`, **`a == c.intern()`** pode ser **`true`** porque ambas referências apontam para a entrada do pool.

## Concatenação `s = s + " world"`

- `String` é **immutável**: `s + " world"` produz uma **nova** `String`; a variável `s` passa a referenciar essa nova instância.
- A `"hello"` anterior, se não houver mais referências, fica **elegível ao GC** (não é “modificação in-place”).

## Nuances

- **`==` em `String`:** quase sempre errado para lógica de negócio; use **`equals`** (e `equalsIgnoreCase` quando fizer sentido).
- **Pool:** não dependa de `==` entre strings vindas de I/O, banco ou `new` sem `intern()` — use `equals`.
- **Performance:** concatenação em laço com `+` pode gerar muitas instâncias intermediárias; para montagem pesada, `StringBuilder` é mais apropriado (outro exemplo/pacote).
