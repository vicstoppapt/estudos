# `MethodsAndParameters`

**PT:** [methodsandparameters.md](methodsandparameters.md)

Example: `MethodsAndParameters.java`.

## Signature

**Method name + parameters + return type** (and `void` when nothing is returned).

- **`cumprimentar(String quem)`** — no return value (`void`); only prints.
- **`somar(int a, int b)`** — returns `int`.

## `return`

Inside a non-`void` method, `return` sends the value back **to the caller** and ends the method at that point.

## Overload

Two methods named **`somar`** with different parameter lists: the compiler picks by **count and types** of arguments at the call site. Changing only the return type is not enough.

## Parameter passing in Java

Always **by value**. For references, the copied value is the **pointer** — full detail is in the `core` module → `PassByValue` / `passbyvalue.md`.
