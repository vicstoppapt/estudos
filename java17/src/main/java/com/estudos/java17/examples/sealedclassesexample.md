# `SealedClassesExample`

Exemplo: `SealedClassesExample.java`.

## Objetivo

**Tipos selados** (`sealed` / `permits`): hierarquia fechada em compile-time — só `Lit` e `Add` implementam `Expr` neste pacote.

## No código

`eval` usa `instanceof` com pattern variable; árvore de expressão com records.

## Nuances

- Permite ao compilador razoar sobre **exaustividade** em `switch` em versões posteriores (Java 21+).
- `permits` pode omitir no mesmo arquivo em certas condições; aqui é explícito.
