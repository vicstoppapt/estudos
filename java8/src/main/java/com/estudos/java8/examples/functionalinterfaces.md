# `FunctionalInterfaces`

**English:** [functionalinterfaces-en.md](functionalinterfaces-en.md)

Exemplo: `FunctionalInterfaces.java`.

## Objetivo

Quatro formas usadas o tempo todo na API funcional do JDK 8:

| Tipo | Método | Papel |
|------|--------|--------|
| `Predicate<T>` | `test` | booleano, filtro |
| `Function<T,R>` | `apply` | transformação T→R |
| `Consumer<T>` | `accept` | consome T, void |
| `Supplier<T>` | `get` | produz T sem argumento |

## Nuances

- `String::length` é **method reference** equivalente a `s -> s.length()` onde o compilador encaixa em `Function`.
- `Math::random` encaixa em `Supplier<Double>` (retorno boxed).
- Composição: `Predicate.and`, `Function.andThen`, etc., para pipelines reutilizáveis.
