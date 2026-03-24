**PT:** [README.md](README.md)

# Java 21

**Repo `estudos`:** [DOCUMENTATION.md](../DOCUMENTATION.md) · [GLOSSARY.md](../GLOSSARY.md).

Focus: **virtual threads**, **sequenced collections**, **record patterns**, and **pattern matching for `switch`** (incl. `when`).

**Documentation:** [`examples/README-en.md`](src/main/java/com/estudos/java21/examples/README-en.md), [`challenges/README-en.md`](src/main/java/com/estudos/java21/challenges/README-en.md) — index + **one `.md` per class**.

## Run

```bash
cd java21
mvn -q compile exec:java -Dexec.mainClass="com.estudos.java21.examples.VirtualThreadsExample"
```

JDK 21 is required for this module.

## Tests

`mvn test` — `ChallengeSequencedPipeline`, `ChallengeVirtualHttp` with Mockito. Examples: `meuPlayground()` in `main` (commented out).
