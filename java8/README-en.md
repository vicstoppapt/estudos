[PT](README.md)

# Java 8

**Repo `estudos`:** [DOCUMENTATION.md](../DOCUMENTATION.md) · [GLOSSARY.md](../GLOSSARY.md).

Focus: **lambdas**, **functional API** (`Stream`, `Optional`), **`java.time`**, **default methods** on interfaces, **method references**.

## Examples (`com.estudos.java8.examples`)

Theory and nuances: **one `.md` per class** next to the `.java` — index at [`examples/README-en.md`](src/main/java/com/estudos/java8/examples/README-en.md).

## Challenges (`com.estudos.java8.challenges`)

Index: [`challenges/README-en.md`](src/main/java/com/estudos/java8/challenges/README-en.md). `mvn test` validates.

## Run

```bash
cd java8
mvn -q compile exec:java -Dexec.mainClass="com.estudos.java8.examples.LambdaBasics"
```

JDK 8+ (compiles with implicit `--release 8` via source/target).

## Tests

`mvn test` — challenges + `PredicateMockitoDemoTest` (Mockito + JUnit 5 + AssertJ).

Each example has `meuPlayground()` (call commented out in `main`).
