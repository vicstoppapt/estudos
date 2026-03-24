[PT](challengereadresource.md)

# `ChallengeReadResource`

Solution: `ChallengeReadResource.java`. Tests use `@TempDir`.

## Goal

Read a file in **UTF-8** with **`Files.readString(Path)`**; `main` uses `sample.txt` at the module root.

## Nuances

- **CWD:** run `main` from the **`java11`** folder where `sample.txt` lives.
- Encoding: UTF-8 by default; specify `Charset` if needed.
