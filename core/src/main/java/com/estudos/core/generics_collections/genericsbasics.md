# `GenericsBasics`

Exemplo em: `GenericsBasics.java`.

## Objetivo

Mostrar duas ideias que costumam confundir no começo: **curinga sem limite** (`List<?>`) e **invariância** de tipos genéricos (`List<String>` não é subtipo de `List<Object>`).

## O que o código demonstra

- **`imprimirLista(List<?> lista)`** — o parâmetro é “lista de algum tipo que não sabemos qual”. Só é seguro tratar cada elemento como **`Object`**: percorrer e imprimir funciona; não dá para **adicionar** um `String` (ou outro tipo concreto) sem quebrar a garantia da lista original, por isso a API de `List<?>` restringe certas operações em tempo de compilação.
- **`main`** cria `List<String>` e passa para `imprimirLista` — isso **compila** porque `List<String>` é compatível com `List<?>` na posição de **consumidor** (só leitura do que já está na lista, no sentido do exemplo).
- A linha comentada **`List<Object> objs = strings;`** — **não compila**. Mesmo que todo `String` seja um `Object`, `List<String>` **não** é subtipo de `List<Object>`: genéricos em Java são **invariantes** na lista inteira. Caso contrário, alguém poderia fazer `objs.add(new Object())` e quebrar a lista que era só de `String`.

## Nuances

- **Erasure:** em bytecode, em geral vira `List` + `Object` onde precisa; o compilador impõe tipos no código-fonte. Por isso às vezes parece “só aviso”, mas a segurança de tipos existe em compile-time.
- **`List<?>` vs `List<Object>`:** `List<?>` significa “lista de tipo desconhecido”; `List<Object>` significa “lista onde se espera poder trabalhar com `Object`” na API — não são intercambiáveis com `List<String>`.
- **PECS** (Producer Extends, Consumer Super) aparece em APIs mais avançadas; este exemplo é o passo anterior: entender `?` e invariância.

## Leitura sugerida

JLS e tutoriais oficiais sobre wildcards e subtyping de tipos parametrizados.
