# `Ola` (single-file)

Fonte: `Ola.java` nesta pasta.

## Objetivo

**Single-file source programs** (Java 11+): executar com `java Ola.java` **sem** passo separado de `javac` para o fluxo didático (o `java` compila em memória).

## Nuances

- Pacote default; não pertence ao pacote `com.estudos.java11.examples` do Maven — útil para experimento rápido no terminal.
- Para código modular/nomeado do projeto, prefira classes sob `src/main/java` com `package` e `mvn exec:java`.

## Como rodar

No diretório `misc`:

```bash
java Ola.java
```
