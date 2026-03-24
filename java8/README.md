# Java 8

**English:** [README-en.md](README-en.md)

**Repo `estudos`:** [DOCUMENTATION.md](../DOCUMENTATION.md) · [GLOSSARY.md](../GLOSSARY.md).

Foco: **lambdas**, **API funcional** (`Stream`, `Optional`), **`java.time`**, **métodos default** em interface, **referências a método**.

## Exemplos (`com.estudos.java8.examples`)

Teoria e nuances: **um `.md` por classe** ao lado do `.java` — índice em [`examples/README.md`](src/main/java/com/estudos/java8/examples/README.md).

## Desafios (`com.estudos.java8.challenges`)

Índice: [`challenges/README.md`](src/main/java/com/estudos/java8/challenges/README.md). `mvn test` valida.

## Executar

```bash
cd java8
mvn -q compile exec:java -Dexec.mainClass="com.estudos.java8.examples.LambdaBasics"
```

JDK 8+ (compila com `--release 8` implícito via source/target).

## Testes

`mvn test` — desafios + `PredicateMockitoDemoTest` (Mockito + JUnit 5 + AssertJ).

Cada exemplo tem `meuPlayground()` (chamada comentada no `main`).
