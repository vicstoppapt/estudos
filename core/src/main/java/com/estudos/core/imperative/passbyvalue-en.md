**PT:** [passbyvalue.md](passbyvalue.md)

# `PassByValue`

Example in: `PassByValue.java`.

## Goal

Anchor Java’s **parameter passing model**: always **by value**. The “value” of a reference-typed variable is **a copy of the pointer** to the object; the “value” of an `int` is **a copy of the number**.

## The three methods

### `tentarTrocarReferencia(StringBuilder sb)`

- Parameter `sb` is a **copy** of the reference `main` passed.
- `sb = new StringBuilder("outro")` only **repoints that copy** to another object. Variable `a` in `main` still points to the same `StringBuilder` as before.
- **Conclusion:** Java does **not** let the caller “rebind” their variable to a different object via reassignment in the parameter.

### `mutarConteudo(StringBuilder sb)`

- `sb` is still a copy of the reference, but **points to the same object** as `a` on the **heap**.
- `append` **changes the internal state** of that object. Anyone else referencing the same object sees the change.
- **Conclusion:** side effect on the shared object is expected; this is not “pass-by-reference” in the C++ sense — it is **two pointers to the same target**.

### `incrementar(int x)`

- `x` is a copy of the primitive. `x++` only changes the local copy.
- **Conclusion:** `n` in `main` stays unchanged.

## Common interview nuance

“Objects are passed by reference” is **informal**; the specification says **pass-by value** where the value is the reference. What matters is predicting behavior: **parameter reassignment** does not leak out; **object mutation** leaks if shared.

## Mental comparison with C++

C++ has explicit references (`int&`). Java has no equivalent for rebinding the caller’s variable.
