# `ChallengeHttpHead`

**English:** [challengehttphead-en.md](challengehttphead-en.md)

Solução: `ChallengeHttpHead.java`. Testes usam mock de `HttpClient`.

## Objetivo

Requisição **HEAD** (sem corpo útil) e retorno do **status**; método **`statusHead(HttpClient, URI)`** injetável para teste.

## Nuances

- `BodyHandlers.discarding()` evita materializar corpo.
- `HttpClient` real em `main` depende de rede; testes isolam com mock.
