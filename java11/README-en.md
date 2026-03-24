[PT](README.md)

# Java 11

**Repo `estudos`:** [DOCUMENTATION.md](../DOCUMENTATION.md) · [GLOSSARY.md](../GLOSSARY.md).

Focus: **`var`**, **`HttpClient`**, **`String`** and **`Files`** utilities, **`Optional`**, **launch without `javac`** for a single file.

**Docs:** [`examples/README-en.md`](src/main/java/com/estudos/java11/examples/README-en.md) and [`challenges/README-en.md`](src/main/java/com/estudos/java11/challenges/README-en.md) — index + **one `.md` per class**; [`misc/README-en.md`](src/main/java/com/estudos/java11/misc/README-en.md) for `Ola.java`.

## Single-file launch (no Maven)

Since Java 11: `java Ola.java` compiles in memory and runs if `main` is in the first file.

```bash
cd src/main/java/com/estudos/java11/misc
java Ola.java
```

See `misc/Ola.java`.

## Challenges

`sample.txt` at the module root for `ChallengeReadResource`’s `main`. See index at [`challenges/README-en.md`](src/main/java/com/estudos/java11/challenges/README-en.md).

## Run (Maven)

```bash
cd java11
mvn -q compile exec:java -Dexec.mainClass="com.estudos.java11.examples.VarInference"
```

## Tests

`mvn test` — challenges (`HttpClient` mocked in the HEAD test, `TempDir` in the file test). Examples: `meuPlayground()` at the end of each `main` (commented out).
