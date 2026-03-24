# `ArraysAndNull`

**English:** [arraysandnull-en.md](arraysandnull-en.md)

Exemplo: `ArraysAndNull.java`.

## Array

`int[] nums = new int[3];` — três posições; primitivos `int` começam em **0** até atribuíres outro valor. Acesso por índice: `nums[0]`.

## Array de referências

`String[] nomes = new String[2];` — cada célula é uma referência; **default** é **`null`** (nenhum objeto).

## `null`

Significa “esta referência **não** aponta para objeto”. Se chamares `s.length()` com `s == null`, a JVM lança **`NullPointerException`**. Por isso o padrão `if (s != null)` antes de usar.

## `Arrays.toString`

Atalho para imprimir conteúdo legível; em código real também se usa laços ou streams (módulo `java8` depois).
