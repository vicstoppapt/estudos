[PT](ola.md)

# `Ola` (single-file)

Source: `Ola.java` in this folder.

## Goal

**Single-file source programs** (Java 11+): run with `java Ola.java` **without** a separate `javac` step for the teaching flow (`java` compiles in memory).

## Nuances

- Default package; not part of the Maven `com.estudos.java11.examples` package — handy for a quick terminal experiment.
- For modular/named project code, prefer classes under `src/main/java` with `package` and `mvn exec:java`.

## How to run

In the `misc` directory:

```bash
java Ola.java
```
