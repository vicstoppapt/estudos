# Java 21

**English:** [README-en.md](README-en.md)

**Repo `estudos`:** [DOCUMENTATION.md](../DOCUMENTATION.md) · [GLOSSARY.md](../GLOSSARY.md).

Foco: **virtual threads**, **sequenced collections**, **record patterns** e **pattern matching for switch** (incl. `when`).

**Documentação:** [`examples/README.md`](src/main/java/com/estudos/java21/examples/README.md), [`challenges/README.md`](src/main/java/com/estudos/java21/challenges/README.md) — índice + **um `.md` por classe**.

## Executar

```bash
cd java21
mvn -q compile exec:java -Dexec.mainClass="com.estudos.java21.examples.VirtualThreadsExample"
```

JDK 21 obrigatório para este módulo.

## Testes

`mvn test` — `ChallengeSequencedPipeline`, `ChallengeVirtualHttp` com Mockito. Exemplos: `meuPlayground()` no `main` (comentado).
