# `basics-memory` — references, RAM, cache

**PT:** [README.md](README.md)

**Repo `estudos`:** [DOCUMENTATION.md](../../DOCUMENTATION.md) · [GLOSSARY.md](../../GLOSSARY.md).

Maven submodule of the `basics` aggregator (`java-basics-parent`). Focus: **mental model of memory**, **references** (what you often call “pointers” in other languages), **hardware** at a high level, **CPU** cache vs JVM/browser.

## Documents (index)

| File | Content |
|------|---------|
| [memoryandreferences-en.md](src/main/java/com/estudos/basics/memory/memoryandreferences-en.md) | RAM, process, JVM, stack/heap, pass-by-value |
| [memoryphysicaldeepdive-en.md](src/main/java/com/estudos/basics/memory/memoryphysicaldeepdive-en.md) | Desktop PC, swap, diagrams, C vs Java |
| [cpucachejvmenavegador-en.md](src/main/java/com/estudos/basics/memory/cpucachejvmenavegador-en.md) | L1/L2/L3, what the app controls |
| [pointersreferencesdeepdive-en.md](src/main/java/com/estudos/basics/memory/pointersreferencesdeepdive-en.md) | Pointers/references in depth + examples |

## Code

| Class | `exec:mainClass` |
|--------|------------------|
| `ReferencesAndPointers` | `com.estudos.basics.memory.ReferencesAndPointers` |

## Build / run

In the **`basics`** (parent) folder:

```bash
mvn -q compile
mvn -q exec:java -pl basics-memory "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"
```

This submodule only:

```bash
cd basics-memory
mvn -q compile exec:java "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"
```

## Sibling module

Minimal language (variables, methods, …): [`../basics-language/README-en.md`](../basics-language/README-en.md) and [`../basics-language/src/main/java/com/estudos/basics/README-en.md`](../basics-language/src/main/java/com/estudos/basics/README-en.md).
