# `CollectionsOverview`

**English:** [collectionsoverview-en.md](collectionsoverview-en.md)

Exemplo em: `CollectionsOverview.java`.

## Objetivo

Dar um **mapa mental** das famílias mais usadas da Collections Framework: **lista ordenada por posição**, **conjunto sem duplicata (no sentido de `equals`)**, **mapa chave→valor**, **fila/deque** — e contrastar **Hash** vs **Linked** vs **Tree** onde o exemplo toca.

## Por bloco no `main`

### `ArrayList` (`List`)

- **Semântica:** sequência com **ordem de inserção** explícita, acesso por índice, **duplicatas permitidas**.
- **Custo típico:** acesso por índice O(1); inserção/remoção no fim amortizado O(1); no meio pode custar O(n) por deslocar elementos.
- **Nuance:** “ordem” aqui é a ordem em que você deu `add`, não ordenação por critério (para isso seria `sort` ou `TreeSet`/`TreeMap`).

### `HashSet` (`Set`)

- **`add` duplicado:** segundo `add("x")` não aumenta `size()` — o conjunto trata igualdade via `equals`/`hashCode` dos elementos.
- **Ordem de iteração:** em `HashSet` **não** é a ordem de inserção nem ordenação natural; é definida pela implementação interna (e pode mudar se a implementação mudar capacidade/rehash). Para ordem previsível, usa-se `LinkedHashSet` ou `TreeSet`.

### `HashMap` (`Map`)

- **Papel:** associação **chave → valor**; chaves únicas no mapa (substitui valor se mesma chave `equals`).
- **Custo típico:** `get`/`put` médio O(1), dependente de `hashCode` bem distribuído e tamanho da tabela.

### `LinkedHashMap`

- **Diferença em relação ao `HashMap`:** mantém **ordem de inserção** (ou ordem de acesso, se configurado em construtor que suporta) nas **iterações** sobre entradas/chaves/valores.
- **Quando importa:** precisão de ordem estável para logs, caches simples, serialização legível, etc.

### `TreeMap`

- **Chaves ordenadas** pela **ordem natural** do tipo da chave (`Comparable`) ou por um **`Comparator`** passado no construtor.
- **Custo:** operações O(log n). Não permite chave `null` (diferente de `HashMap`, que permite uma chave `null`).

### `ArrayDeque` como `Queue`

- **`poll()`** remove e devolve o **primeiro** da fila (comportamento de fila FIFO no uso mostrado).
- **Nuance:** `ArrayDeque` é geralmente preferível a `Stack` legada para pilha/fila; aqui só ilustra fila simples.

## Comparando “Hash / Linked / Tree” em uma frase

| Ideia | Hash | Linked (Hash…) | Tree |
|--------|------|----------------|------|
| Ordem de iteração | não garante inserção/natural | inserção (LinkedHash*) | ordenada por chave/elemento |
| Custo típico | O(1) médio map/set | um pouco mais overhead | O(log n) |

## Limitações deste exemplo

Só instancia e imprime; não mostra **thread-safety** (`ConcurrentHashMap`, etc.), nem **fail-fast** de iteradores, nem `NavigableMap`/`SortedSet`. Serve como âncora para a documentação oficial da interface (`List`, `Set`, `Map`, `Queue`).
