# `PatternMatchingInstanceof`

**English:** [patternmatchinginstanceof-en.md](patternmatchinginstanceof-en.md)

Exemplo: `PatternMatchingInstanceof.java`.

## Objetivo

**`instanceof` com binding:** `if (o instanceof String s && s.length() > 2)` — `s` tipado e em escopo no bloco.

## Nuances

- A parte após `&&` só avalia se o `instanceof` for verdadeiro (**short-circuit**).
- Evita cast manual ruidoso.
- Evolui para **pattern matching em switch** no Java 21 (outro módulo).
