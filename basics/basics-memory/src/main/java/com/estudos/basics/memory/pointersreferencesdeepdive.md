# Ponteiros e referências em detalhe (Java)

**English:** [pointersreferencesdeepdive-en.md](pointersreferencesdeepdive-en.md)

Texto do submódulo **`basics-memory`**. Código que acompanha: [`ReferencesAndPointers.java`](ReferencesAndPointers.java). Visão mais larga (stack, heap, entrevista): [memoryandreferences.md](memoryandreferences.md). C vs Java e hardware: [memoryphysicaldeepdive.md](memoryphysicaldeepdive.md).

---

## 1. “Ponteiro” em Java = **referência**

Em **C/C++**, um *ponteiro* é um endereço numérico que podes **aritmetizar** (`p++`, saltar na memória) e **reinterpretar** (casts perigosos).

Em **Java**, a variável de tipo referência (`String s`, `int[] a`, `Object o`) guarda um valor que a **JVM** interpreta como “identificador lógico” de um objeto no **heap**. Na prática, por baixo, implementações como HotSpot usam endereços ou *compressed oops* — **detalhe de implementação**. O que a **linguagem** garante:

- Não há aritmética de ponteiro exposta ao programador.
- Não acedes a memória arbitrária como em C.
- Há **coletor de lixo**; objetos sem referências alcançáveis podem ser libertados.

Por isso, em conversa técnica, é mais preciso dizer **referência** do que “ponteiro C” — mas o modelo mental **“aponta para um objeto”** é o mesmo.

---

## 2. O que vive onde (recapitulação operacional)

| O quê | Onde (modelo usual) |
|--------|----------------------|
| Variável local **primitiva** (`int`, `boolean`, …) | **Stack** (frame do método) |
| Variável local **referência** | **Stack** — o *slot* guarda bits da referência |
| Objeto (`new`, array, `String` internamente, …) | **Heap** |
| Campos de instância | Dentro do objeto no **heap**; o objeto é alcançável por referência |

Duas variáveis locais podem guardar **a mesma referência** → os dois “apontam” para o **mesmo** objeto heap.

---

## 3. Identidade vs igualdade de conteúdo

- **`==` em referências** → **mesma identidade**: as duas variáveis referem-se ao **mesmo** objeto (mesmo endereço lógico).
- **`equals`** (quando bem definido) → **mesmo conteúdo** segundo o contrato da classe (ex.: duas `String` distintas com o texto `"olá"`).

Exemplo mental:

```java
String a = new String("x");
String b = new String("x");
String c = a;
```

- `a == b` → **false** (dois objetos no heap).
- `a.equals(b)` → **true** (mesmo texto).
- `a == c` → **true** (mesma referência).

Para depuração, `System.identityHashCode(ref)` devolve um valor derivado da identidade do objeto (não uses como “ID de negócio”; é ferramenta de diagnóstico / curiosidade).

---

## 4. Partilha de referência: um objeto, várias variáveis

```java
int[] v = {1, 2, 3};
int[] w = v;
w[0] = 99;
// v[0] também é 99
```

`v` e `w` são **dois slots** na stack (ou campos distintos), cada um com uma **cópia do valor da referência** — e esse valor é **o mesmo**: o array existe **uma vez** no heap.

Isto explica muitos bugs: passas “o array” a um método e o método altera índices → o chamador vê a alteração, porque **só houve uma** estrutura no heap.

---

## 5. Passagem de parâmetros: cópia da referência

Java passa **sempre por valor**. Se o parâmetro é referência, o **valor** copiado é o **valor da referência** (como um “ponteiro” copiado).

```java
void m(int[] a) {
    a[0] = -1;           // altera o objeto heap → chamador vê
    a = new int[]{9};    // só muda o parâmetro local `a`; chamador NÃO vê
}
```

Fluxo mental:

1. O chamador tem `int[] dados` com referência **R** para um array **A**.
2. Ao chamar `m(dados)`, o parâmetro `a` recebe uma **cópia** de **R** → também aponta para **A**.
3. `a[0] = -1` mexe em **A**.
4. `a = new int[]{9}` faz `a` apontar para **outro** array; o chamador continua com **R** → **A** inalterado nessa reassignment (mas já pode ter sido mutado no passo 3).

Isto está exercitado em [`ReferencesAndPointers.java`](ReferencesAndPointers.java).

---

## 6. `null`

`null` é a referência que **não** aponta para objeto algum. Desreferenciar (`algo.campo`, `algo.metodo()`) com `null` → `NullPointerException`.

Útil para “ainda não há objeto” ou opcional (em código moderno muitas vezes preferes `Optional` para retornos; `null` ainda aparece em APIs legadas).

---

## 7. Arrays são objetos heap

`int[]`, `Object[]`, … são **objetos**. A variável é referência; `.length` e os índices vivem nesse objeto. Arrays multidimensionais em Java são **arrays de referências** para outros arrays (exceto o último nível de primitivos).

---

## 8. `final` e referência (ligação com `basics-language`)

`private final List<X> itens = new ArrayList<>();` → não podes fazer `itens = outraLista`; **podes** mutar o **conteúdo** da lista com `add`, porque o objeto `ArrayList` é o mesmo. Ver [finalstaticinterfacesandencap.md](../../../../../../../../basics-language/src/main/java/com/estudos/basics/finalstaticinterfacesandencap.md) no módulo irmão.

---

## 9. C vs Java (resumo)

| Aspeto | C (ponteiro) | Java (referência) |
|--------|----------------|-------------------|
| Aritmética | `p + 1` comum | Não existe |
| Endereço bruto | `void*`, casts | Não exposto |
| Memória livre | `free` manual | GC |
| Referência nula | `NULL` | `null` |

Mais detalhe e diagramas: [memoryphysicaldeepdive.md](memoryphysicaldeepdive.md).

---

## 10. Erros típicos de raciocínio

1. Achar que `==` em `String` compara texto (compara identidade, salvo *string literals* internados no mesmo literal — tema em `VariablesAndTypes` / pool).
2. Achar que passar um objeto **copia** o objeto inteiro (só copia a referência).
3. Achar que `final List` impede `add` (impede **trocar** a variável de lista, não mutar o objeto lista).
4. Confundir **duas referências iguais** com **dois objetos `equals`** — essenciais para `HashMap` / `HashSet` (`core` → `EqualsHashCodeContract`).

---

## 11. Onde continuar neste repositório

| Destino | Tema |
|---------|------|
| [memoryandreferences.md](memoryandreferences.md) | Stack/heap, threads, entrevista |
| `basics-language` → `VariablesAndTypes` | `==` vs `equals`, wrappers |
| `core` → `PassByValue` | `StringBuilder` e reatribuição de parâmetro |
| `core` → `jvmmemorymodelintro.md` | JVM em profundidade relativa ao `basics` |
