# `VirtualThreadsExample`

Exemplo: `VirtualThreadsExample.java`.

## Objetivo

**Virtual threads** (Java 21): `Executors.newVirtualThreadPerTaskExecutor()` — muitas tarefas bloqueantes sem um thread OS por tarefa.

## Nuances

- **Não** é “mais rápido” para CPU pura; brilha em **I/O** bloqueante massivo.
- **Pinning:** synchronized dentro de VT pode limitar escalonamento — há evoluções e boas práticas na documentação do JDK.
- `try-with-resources` fecha o executor ao fim.
