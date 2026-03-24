# `ObjectMethods`

**English:** [objectmethods-en.md](objectmethods-en.md)

Exemplo em: `ObjectMethods.java` (classe interna `Ponto`).

## Objetivo

Mostrar a **tríade** `toString` / `equals` / `hashCode` coerente para um valor imutável simples (`x`, `y`).

## `toString`

- Sobrescrito para saída **legível** e estável para debug (`Ponto(1,2)`).
- Sem isso, `println` mostraria nome da classe + `@hash` hexadecimal pouco informativo.

## `equals(Object o)`

- **Reflexividade curta:** `this == o` → `true` (mesma referência).
- **Tipo:** se não for `instanceof Ponto`, retorna `false` — evita `ClassCastException` e respeita simetria com outros tipos.
- **Campos:** compara `x` e `y` — igualdade de **valor** para duas instâncias distintas no heap.

## `hashCode`

- Implementado com **`Objects.hash(x, y)`** — deve usar **os mesmos campos** que entram em `equals`.
- **Contrato:** se `a.equals(b)`, então `a.hashCode() == b.hashCode()`. O contrário não precisa valer (colisões são possíveis), mas `equals` bem feito + `hashCode` alinhado evita bugs em `HashMap`/`HashSet`.

## O que o `main` prova

- Duas instâncias **`new Ponto(1,2)`** distintas: **`equals` true** apesar de referências diferentes.
- `toString` reflete o valor.

## Nuance

Para tipos com `double` ou comparação aproximada, `equals` pode precisar de outra política; para `int`, igualdade bit a bit é a esperada.
