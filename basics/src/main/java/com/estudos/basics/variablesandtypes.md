# `VariablesAndTypes`

Exemplo: `VariablesAndTypes.java`.

**Memória física, stack/heap, pass-by-value vs “referência”, comparação com outras linguagens e ROM/RAM:** ver o texto transversal [memoryandreferences.md](memoryandreferences.md).

Este arquivo junta o que costuma faltar quando alguém diz “sei variáveis”: **o que a JVM guarda na variável** quando o tipo é primitivo vs quando é **referência a objeto** (como `String`), e **por que** existem tipos “não primitivos”.

---

## Onde ver mais no repositório

| Tema | Onde |
|------|------|
| Passar `int` vs passar referência num método | `core` → `PassByValue` / `passbyvalue.md` |
| `String`, `==` vs `equals`, pool | `core` → `StringImmutability` / `stringimmutability.md` |
| Stack, heap, modelo de memória (visão geral) | `core` → `JvmMemoryModelIntro` / `jvmmemorymodelintro.md` |
| RAM vs disco, referências na entrevista, outras linguagens | **este módulo** → [memoryandreferences.md](memoryandreferences.md) |
| Cache CPU vs navegador, JVM não “coloca” dados no L1 | [cpucachejvmenavegador.md](cpucachejvmenavegador.md) |

---

## O que é uma variável (para o Java)

Um **nome** num método (variável **local**) ou numa classe (campo) que associa a um **valor** que a máquina consegue ler e escrever.

O ponto importante: **o “formato” desse valor depende do tipo**:

- Em tipo **primitivo**, a variável guarda **o próprio valor** (o número, o booleano, o carácter codificado).
- Em tipo **referência** (qualquer **classe** ou interface, incluindo `String`, `Integer`, `List`, etc.), a variável guarda **uma referência** — em termos mentais, um “endereço” ou ponteiro para um **objeto** que vive noutro sítio (tipicamente o **heap**).

O Java **não** expõe referências como números que possas mexer (diferente de C). Você só usa o nome da variável e o compilador/JVM tratam do resto.

---

## Primitivos: lista e ideia

São **oito** tipos em Java:

| Tipo | Exemplo | Notas |
|------|---------|--------|
| `byte`, `short`, `int`, `long` | `int n = 3;` | inteiros com tamanhos diferentes |
| `float`, `double` | `double x = 1.5;` | ponto flutuante |
| `boolean` | `boolean ok = true;` | só `true` / `false` |
| `char` | `char c = 'A';` | um código Unicode UTF-16 (16 bits); **não** é `String` |

**Como o Java “pensa”:** estes valores são **normais** para a CPU: cabem em registos ou na stack do método, e operações (`+`, `==`, etc.) são diretas. **Não** há cabeçalho de objeto, nem métodos “ligados” ao valor — por isso não podes fazer `5.toString()`; usas `Integer.toString(5)` ou concatenação.

**Default** em campos de classe se não inicializares: `0`, `false`, `'\u0000'`, conforme o tipo.

---

## Tipos que **não** são primitivos (referência / objeto)

Qualquer **classe** (incluindo `String`, `Integer`, `Object`, arrays `int[]` são também “referência” embora o Java os trate com sintaxe especial).

- A variável do tipo `String` **não** contém os caracteres “dentro dela” no mesmo sentido que `int` contém o bit pattern do número: contém **referência** ao objeto `String` no heap, onde está o conteúdo (e metadados internos).
- **`null`** só faz sentido em tipo referência: significa “não aponta para nenhum objeto”.

**`String` em concreto:** é classe imutável; literais `"ana"` são atalhos para objetos `String`. Comparar conteúdo → **`equals`**, não `==` (exceto casos específicos de pool — vê `stringimmutability.md`).

---

## Por que existem tipos que não são primitivos?

1. **Objetos têm comportamento e estrutura:** `String` tem `length()`, `substring()`, etc. Um `int` sozinho não tem métodos; é só valor.
2. **Coleções e APIs genéricas:** `List<int>` **não existe** em Java (até `List<int>` com tipos primitivos em versões muito recentes com regras especiais). Historicamente usas `List<Integer>` — **wrapper** — porque genéricos trabalham com **tipos referência**.
3. **Nullabilidade semântica:** `Integer podeSerNull` pode ser `null` para “ausente”; `int` não pode ser `null` (usa wrappers ou `Optional` em APIs modernas).
4. **Herança e polimorfismo:** podes ter `Object ref = "texto";` — um supertipo comum; primitivos não participam dessa hierarquia (salvo **boxing**).

---

## Wrappers (`Integer`, `Double`, …)

Cada primitivo tem um tipo “caixa”: `Integer` para `int`, `Double` para `double`, etc.

- São **classes** → instâncias no **heap** (exceto otimizações como **integer caching** para valores pequenos).
- **Autoboxing:** `Integer x = 5;` o compilador traduz para algo como `Integer.valueOf(5)`.
- **Unboxing:** `int n = x;` gera chamada a `x.intValue()` (e pode lançar `NullPointerException` se `x` for `null`).

Servem para integrar primitivos com APIs orientadas a objetos (coleções, `Object`, reflexão, etc.).

---

## Modelo mental rápido: stack vs heap (local)

- **Variáveis locais** primitivas: o valor costuma viver no **frame** do método (ligado à ideia de **stack** por thread).
- **Variáveis locais** referência: no frame fica a **referência**; o **objeto** apontado fica no **heap**.

Isto é simplificado; o JIT pode otimizar. Para precisão e threads, `jvmmemorymodelintro.md` no `core`.

---

## `final`

`final int limite = 10;` — depois de inicializar, **você não pode** reatribuir `limite`. O objeto referenciado por uma variável `final` **pode** mudar de estado **interno** se o objeto for mutável; só você não pode fazer a variável apontar para **outro** objeto (`final String s` não pode receber outra `String`, mas `String` é imutável de qualquer forma).

---

## Próximo passo no módulo `basics`

`methodsandparameters.md` — como métodos recebem primitivos vs referências (e depois `core` → `passbyvalue.md` aprofunda).
