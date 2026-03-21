# `DateTimeApi`

Exemplo: `DateTimeApi.java`.

## Objetivo

API **`java.time`**: tipos **imutáveis** e separação entre data local, data/hora local, instante global e duração.

## No código

- **`LocalDate` / `LocalDateTime`:** relógio local da JVM, **sem** fuso explícito.
- **`ZonedDateTime` com `ZoneId`:** instante civil numa região; regras de DST vêm da base de dados da JVM.
- **`Instant` + `Duration`:** linha do tempo UTC; `between` mede intervalo entre instantes.

## Nuances

- Evitar `Date`/`Calendar` legados para código novo.
- **Persistência:** armazenar preferencialmente `Instant` ou ISO-8601 explícito; converter na borda (UI/API).
- **Parsing/formatting:** `DateTimeFormatter` thread-safe.
