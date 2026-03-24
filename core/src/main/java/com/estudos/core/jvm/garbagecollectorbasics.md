# `GarbageCollectorBasics`

**English:** [garbagecollectorbasics-en.md](garbagecollectorbasics-en.md)

Exemplo em: `GarbageCollectorBasics.java`.

## Objetivo

Três ideias: **memória aproximada** via `Runtime`, **alocação** visível como aumento de uso, e **soltar referência** para tornar objeto **elegível** à coleta — sem prometer **quando** o GC roda.

## O código linha a linha (conceitual)

- **`Runtime.getRuntime()`** — singleton da aplicação para consultas grossas de memória.
- **`totalMemory() - freeMemory()`** — **aproximação** de “usado”; a JVM pode expandir/contrair heap e o significado de “livre” não é um medidor preciso de laboratório.
- **`new byte[1024 * 1024]`** — aloca array grande; frequentemente o “usado” **sobe** entre o snapshot antes e depois (não é garantido em todos os momentos por causa de GC concorrente ou ajustes internos, mas é o efeito típico em exemplo didático).
- **`blob = null`** — remove a **única** referência forte do exemplo ao array; o objeto passa a ser **elegível** para GC.
- O print final lembra: **não há garantia de tempo** de coleta; **`System.gc()`** é só sugestão e não deve ser base de design.

## Nuances

- **Finalize:** evitar `finalize` para liberação de recursos; preferir `try-with-resources` / `Cleaner` onde aplicável.
- **Gerações / collectors:** desde JDK 9+ o padrão evoluiu (G1, depois outros conforme versão). O exemplo não fixa qual algoritmo — só o conceito de **reachability**.
- **Profiling real:** para diagnóstico sério, usar **JFR**, **VisualVM**, flags `-Xlog:gc`, etc.

## Erro comum de interpretação

Diferença entre “memória voltou ao SO” e “objeto foi coletado” — a JVM pode manter regiões reservadas; o que importa para vazamento é **referências acidentais** mantendo objetos vivos.
