# `ChallengeRecordValidation`

**English:** [challengerecordvalidation-en.md](challengerecordvalidation-en.md)

Solução: `ChallengeRecordValidation.java`. Testes validam invariantes.

## Objetivo

**Compact constructor** de record: valida `Email` (não vazio, contém `@`) antes de expor a instância.

## Nuances

- Compact constructor não lista parâmetros; atribuição aos campos ocorre após o bloco (semântica do record).
- Lançar `IllegalArgumentException` é padrão para argumentos inválidos.
