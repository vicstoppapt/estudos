# Java 17

**English:** [README-en.md](README-en.md)

**Repo `estudos`:** [DOCUMENTATION.md](../DOCUMENTATION.md) · [GLOSSARY.md](../GLOSSARY.md).

Foco: **records**, **sealed classes**, **pattern matching para `instanceof`**, **text blocks**, **switch como expressão**.

**Documentação:** [`examples/README.md`](src/main/java/com/estudos/java17/examples/README.md), [`challenges/README.md`](src/main/java/com/estudos/java17/challenges/README.md) — índice + **um `.md` por classe**.

## Executar

```bash
cd java17
mvn -q compile exec:java -Dexec.mainClass="com.estudos.java17.examples.RecordsExample"
```

## Testes

`mvn test` — desafios + AssertJ. Exemplos com `meuPlayground()` comentado no `main`.
