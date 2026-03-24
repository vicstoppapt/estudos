# `ObjectsConstructorsThis`

**English:** [objectsconstructorsthis-en.md](objectsconstructorsthis-en.md)

Exemplo: `ObjectsConstructorsThis.java` (classe interna `Lampada`).

## Classe e objeto

- **Classe** (`Lampada`): “molde” / tipo — define campos e métodos.
- **Objeto** (`new Lampada("sala")`): **instância** concreta na memória; cada uma tem o seu próprio estado (`ligada`, etc.).

## `new` e construtor

`new Lampada("sala")` **aloca** o objeto e chama o **construtor** `Lampada(String id)` para inicializar campos.

## `this`

Dentro do construtor, `this.id = id` significa: o campo **`id` deste objeto** recebe o valor do **parâmetro** `id`. Sem `this`, em outros contextos pode haver ambiguidade de nomes.

## Campos `private`

Só código **dentro da mesma classe** acessa diretamente. O “mundo de fora” usa métodos como `ligar()` e `resumo()` — isto chama-se **encapsulamento** (noções maiores em OOP no `core`).
