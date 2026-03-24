**PT:** [README.md](README.md)

# Java 17

**Repo `estudos`:** [DOCUMENTATION.md](../DOCUMENTATION.md) · [GLOSSARY.md](../GLOSSARY.md).

Focus: **records**, **sealed classes**, **pattern matching for `instanceof`**, **text blocks**, **`switch` as an expression**.

**Documentation:** [`examples/README-en.md`](src/main/java/com/estudos/java17/examples/README-en.md), [`challenges/README-en.md`](src/main/java/com/estudos/java17/challenges/README-en.md) — index + **one `.md` per class**.

## Run

```bash
cd java17
mvn -q compile exec:java -Dexec.mainClass="com.estudos.java17.examples.RecordsExample"
```

## Tests

`mvn test` — challenges + AssertJ. Examples use `meuPlayground()` commented out in `main`.
