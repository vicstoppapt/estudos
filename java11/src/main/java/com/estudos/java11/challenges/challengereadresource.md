# `ChallengeReadResource`

**English:** [challengereadresource-en.md](challengereadresource-en.md)

Solução: `ChallengeReadResource.java`. Testes usam `@TempDir`.

## Objetivo

Ler arquivo em **UTF-8** com **`Files.readString(Path)`**; `main` usa `sample.txt` na raiz do módulo.

## Nuances

- **CWD:** rode `main` a partir da pasta **`java11`** onde está `sample.txt`.
- Encoding: UTF-8 default; especifique `Charset` se necessário.
