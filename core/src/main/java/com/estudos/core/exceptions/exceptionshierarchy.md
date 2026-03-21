# `ExceptionsHierarchy`

Exemplo em: `ExceptionsHierarchy.java`.

## Objetivo

Fixar a **hierarquia** (`Throwable` → `Error` / `Exception`), a distinção **checked** vs **unchecked**, e o papel do **`finally`** na ordem de saída do `try` (inclusive com `return`).

## `finallySempreRoda()`

- Há `return 1` no `try`, mas o **`finally` executa antes** de o método efetivamente retornar ao chamador.
- Na prática: side effects no `finally` (logs, liberar recurso complementar) rodam; o valor devolvido continua sendo o do `try` salvo override por `return` no próprio `finally` (anti-padrão).

## Checked: `IOException`

- `Files.readString` declara **`IOException`**. O compilador **obriga** `try/catch` ou `throws` na assinatura.
- O exemplo usa arquivo inexistente para cair no `catch` de forma controlada.

## Unchecked: `ArithmeticException`

- Subtipo de **`RuntimeException`**. Não precisa declarar na assinatura; ainda assim pode ser capturada com `try/catch` se quiser tratar localmente.
- `1 / 0` dispara `ArithmeticException`.

## Nuances

- **`try-with-resources`** e **supressed exceptions** — próximo passo após este exemplo.
- **Não engolir** exceções sem log; aqui só imprime tipo/mensagem para estudo.

## `meuPlayground()`

Experimente multi-catch, `try-with-resources` ou relançar com causa (`initCause`).
