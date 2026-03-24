**PT:** [exceptionshierarchy.md](exceptionshierarchy.md)

# `ExceptionsHierarchy`

Example in: `ExceptionsHierarchy.java`.

## Goal

Anchor the **hierarchy** (`Throwable` → `Error` / `Exception`), **checked** vs **unchecked**, and the role of **`finally`** in exit order from `try` (including with `return`).

## `finallySempreRoda()`

- There is `return 1` in `try`, but **`finally` runs before** the method actually returns to the caller.
- In practice: side effects in `finally` (logs, complementary resource release) run; the returned value stays the one from `try` unless overridden by `return` in `finally` itself (anti-pattern).

## Checked: `IOException`

- `Files.readString` declares **`IOException`**. The compiler **requires** `try/catch` or `throws` on the signature.
- The example uses a non-existent file to land in `catch` in a controlled way.

## Unchecked: `ArithmeticException`

- Subtype of **`RuntimeException`**. Does not need to be declared on the signature; can still be caught with `try/catch` if you want local handling.
- `1 / 0` throws `ArithmeticException`.

## Nuances

- **`try-with-resources`** and **suppressed exceptions** — next step after this example.
- **Do not swallow** exceptions without logging; here it only prints type/message for study.

## `meuPlayground()`

Try multi-catch, `try-with-resources`, or rethrow with cause (`initCause`).
