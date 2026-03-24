# `VarInference`

**English:** [varinference-en.md](varinference-en.md)

Exemplo: `VarInference.java`.

## Objetivo

**Inferência de tipo local** com `var`: o tipo é deduzido do **inicializador**; útil para nomes longos e para `new` com tipo genérico explícito no lado direito.

## Limitações (não use `var` quando piora leitura)

- Sem inicializador na mesma linha → não compila.
- Não em parâmetros de método, não em tipo de campo (até Java atual), não quando o tipo inferido for ambíguo demais para humanos.
- **`var` não é** “tipo dinâmico” — continua fortemente tipado em compile-time.

## No código

`var lista` é `ArrayList<String>`; `for (var s : lista)` infere elemento como `String`. `List.of` retorna lista **imutável** (Java 9+).
