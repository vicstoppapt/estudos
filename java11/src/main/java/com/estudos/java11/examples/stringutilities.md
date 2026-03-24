# `StringUtilities`

**English:** [stringutilities-en.md](stringutilities-en.md)

Exemplo: `StringUtilities.java`.

## Objetivo

Novidades de **`String`** no Java 11 usadas no dia a dia.

## Métodos

- **`isBlank`:** true para vazio ou só *whitespace* Unicode (não só espaço ASCII).
- **`strip`:** remove whitespace leading/trailing segundo Unicode (`trim` é mais restrito a BMP clássico).
- **`lines`:** `Stream<String>` de linhas lógicas (sem `\n` finais fantasmas como `split` bruto).
- **`repeat`:** repetição eficiente sem laço manual.

## Nuances

`strip` vs `trim`: preferir `strip` em código novo para consistência Unicode.
