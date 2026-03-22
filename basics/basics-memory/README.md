# `basics-memory` — referências, RAM, cache

Submódulo Maven do agregador `basics` (`java-basics-parent`). Foco: **modelo mental de memória**, **referências** (o que costumas chamar “ponteiros” noutras linguagens), **hardware** em alto nível, **cache** CPU vs JVM/navegador.

## Documentos (índice)

| Ficheiro | Conteúdo |
|----------|-----------|
| [memoryandreferences.md](src/main/java/com/estudos/basics/memory/memoryandreferences.md) | RAM, processo, JVM, stack/heap, pass-by-value |
| [memoryphysicaldeepdive.md](src/main/java/com/estudos/basics/memory/memoryphysicaldeepdive.md) | Gabinete, swap, diagramas, C vs Java |
| [cpucachejvmenavegador.md](src/main/java/com/estudos/basics/memory/cpucachejvmenavegador.md) | L1/L2/L3, o que a app controla |
| [pointersreferencesdeepdive.md](src/main/java/com/estudos/basics/memory/pointersreferencesdeepdive.md) | Ponteiros/referências em detalhe + exemplos |

## Código

| Classe | `exec:mainClass` |
|--------|------------------|
| `ReferencesAndPointers` | `com.estudos.basics.memory.ReferencesAndPointers` |

## Compilar / executar

Na pasta **`basics`** (pai):

```bash
mvn -q compile
mvn -q exec:java -pl basics-memory "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"
```

Só este submódulo:

```bash
cd basics-memory
mvn -q compile exec:java "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"
```

## Irmão

Linguagem mínima (variáveis, métodos, …): [`../basics-language/README.md`](../basics-language/README.md) e [`../basics-language/src/main/java/com/estudos/basics/README.md`](../basics-language/src/main/java/com/estudos/basics/README.md).
