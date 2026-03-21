# `RecordsExample`

Exemplo: `RecordsExample.java`.

## Objetivo

**Record** (Java 16+): tipo imutável com estado no cabeçalho; compilador gera construtor canônico, acessores, `equals`/`hashCode`/`toString`.

## Nuances

- Campos são `private final` implícitos; acessores são `nome()` sem prefixo `get`.
- Pode adicionar métodos, compact constructors para validação, interfaces implementadas.
- Não é “só DTO”: pode ter invariantes e comportamento.
