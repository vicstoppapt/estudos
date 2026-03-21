# Java 11

Foco: **`var`**, **`HttpClient`**, utilitários de **`String`** e **`Files`**, **`Optional`**, **launch sem `javac`** para arquivo único.

**Documentação:** [`examples/README.md`](src/main/java/com/estudos/java11/examples/README.md) e [`challenges/README.md`](src/main/java/com/estudos/java11/challenges/README.md) — índice + **um `.md` por classe**; [`misc/README.md`](src/main/java/com/estudos/java11/misc/README.md) para `Ola.java`.

## Launch single-file (sem Maven)

Desde Java 11: `java Ola.java` compila em memória e executa se houver `main` no primeiro arquivo.

```bash
cd src/main/java/com/estudos/java11/misc
java Ola.java
```

Veja `misc/Ola.java`.

## Desafios

`sample.txt` na raiz do módulo para o `main` de `ChallengeReadResource`. Ver índice em [`challenges/README.md`](src/main/java/com/estudos/java11/challenges/README.md).

## Executar (Maven)

```bash
cd java11
mvn -q compile exec:java -Dexec.mainClass="com.estudos.java11.examples.VarInference"
```

## Testes

`mvn test` — desafios (`HttpClient` mockado no teste de HEAD, `TempDir` no de arquivo). Exemplos: `meuPlayground()` no fim de cada `main` (comentado).
