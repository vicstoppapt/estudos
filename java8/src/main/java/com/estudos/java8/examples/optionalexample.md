# `OptionalExample`

Exemplo: `OptionalExample.java`.

## Objetivo

Representar **ausência** de valor sem `null` na assinatura da API de busca; `orElse` e `ifPresent`.

## Nuances

- **`Optional` não é** substituto de todos os nulls em campos — uso típico é **retorno** de método.
- **`Optional.of(null)`** lança exceção; use `ofNullable`.
- **`orElseGet(Supplier)`** evita avaliar fallback caro quando há valor.
- Não serializar `Optional` em DTOs sem necessidade.

## Anti-padrão

`Optional` como parâmetro de método em excesso — em geral prefira overloads ou clareza do contrato.
