# `ArraysAndNull`

**PT:** [arraysandnull.md](arraysandnull.md)

Example: `ArraysAndNull.java`.

## Array

`int[] nums = new int[3];` — three slots; primitive `int` values start at **0** until you assign something else. Access by index: `nums[0]`.

## Array of references

`String[] nomes = new String[2];` — each cell is a reference; **default** is **`null`** (no object).

## `null`

Means “this reference does **not** point to an object”. If you call `s.length()` with `s == null`, the JVM throws **`NullPointerException`**. Hence the `if (s != null)` pattern before use.

## `Arrays.toString`

Shortcut to print readable content; in real code you also use loops or streams (`java8` module later).
