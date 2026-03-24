**PT:** [declarativevsimperative.md](declarativevsimperative.md)

# `DeclarativeVsImperative`

Example in: `DeclarativeVsImperative.java`.

## Goal

Compare the **same outcome** with an **imperative** loop (`for` + accumulator) and a **declarative** pipeline (`stream` + intermediate ops + terminal).

## Common behavior

Both sum only **even** integers and **ignore `null`** in the list (`Arrays.asList` allows `null` here only for defensive practice).

## Imperative version

- Mutable `total` variable, explicit iteration, condition `n != null && n % 2 == 0`.
- Easy to debug step by step in the debugger.

## Declarative version

- `filter` applies the same predicate; `mapToInt(Integer::intValue)` avoids boxing in the sum; `sum()` is terminal and forces pipeline execution.
- **Lazy:** nothing runs until the terminal (`sum`).

## Nuances

- **Performance:** irrelevant for tiny lists; for large streams, streams may have overhead or win with parallelization (`parallelStream`) — use care and profiling.
- **Readability:** teams often prefer streams for chained transforms; loops for heavily branching logic.
- Prerequisite: comfort with lambdas — see the **java8** module.

## `meuPlayground()`

Change the predicate (e.g. odds), use `reduce`, or another collection.
