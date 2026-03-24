# `MethodsAndParameters`

**English:** [methodsandparameters-en.md](methodsandparameters-en.md)

Exemplo: `MethodsAndParameters.java`.

## Assinatura

**Nome do método + parâmetros + tipo de retorno** (e `void` quando não devolve nada).

- **`cumprimentar(String quem)`** — não devolve valor (`void`); só imprime.
- **`somar(int a, int b)`** — devolve `int`.

## `return`

Dentro de um método não-`void`, `return` envia o valor de volta **ao chamador** e termina o método naquele ponto.

## Sobrecarga (overload)

Dois métodos **`somar`** com listas de parâmetros diferentes: o compilador escolhe pela **quantidade e tipos** dos argumentos na chamada. Não basta mudar só o tipo de retorno.

## Passagem de parâmetros em Java

Sempre **por valor**. Para referências, o valor copiado é o **ponteiro** — o detalhe completo está no módulo `core` → `PassByValue` / `passbyvalue.md`.
