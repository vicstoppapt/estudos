# Pointers and references in depth (Java)

**PT:** [pointersreferencesdeepdive.md](pointersreferencesdeepdive.md)

Text in the **`basics-memory`** submodule. Companion code: [`ReferencesAndPointers.java`](ReferencesAndPointers.java). Broader view (stack, heap, interviews): [memoryandreferences-en.md](memoryandreferences-en.md). C vs Java and hardware: [memoryphysicaldeepdive-en.md](memoryphysicaldeepdive-en.md).

---

## 1. “Pointer” in Java = **reference**

In **C/C++**, a *pointer* is a numeric address you can **arithmetize** (`p++`, jump in memory) and **reinterpret** (dangerous casts).

In **Java**, a reference-type variable (`String s`, `int[] a`, `Object o`) holds a value the **JVM** treats as a logical “identifier” for a heap object. In practice, HotSpot-style implementations use addresses or *compressed oops* — **implementation detail**. What the **language** guarantees:

- No pointer arithmetic exposed to the programmer.
- No arbitrary memory access like in C.
- There is a **garbage collector**; objects with no reachable references may be freed.

So in technical conversation, **reference** is more precise than “C pointer” — but the mental model **“points to an object”** is the same.

---

## 2. What lives where (operational recap)

| What | Where (usual model) |
|------|---------------------|
| **Primitive** local (`int`, `boolean`, …) | **Stack** (method frame) |
| **Reference** local | **Stack** — the *slot* holds reference bits |
| Object (`new`, array, `String` internally, …) | **Heap** |
| Instance fields | Inside the object on the **heap**; object reachable via reference |

Two locals may hold **the same reference** → both “point” to the **same** heap object.

---

## 3. Identity vs content equality

- **`==` on references** → **same identity**: both variables refer to the **same** object (same logical address).
- **`equals`** (when well defined) → **same content** per the class contract (e.g. two distinct `String` objects with text `"olá"`).

Mental example:

```java
String a = new String("x");
String b = new String("x");
String c = a;
```

- `a == b` → **false** (two heap objects).
- `a.equals(b)` → **true** (same text).
- `a == c` → **true** (same reference).

For debugging, `System.identityHashCode(ref)` returns a value derived from object identity (do not use as “business ID”; diagnostic / curiosity only).

---

## 4. Reference sharing: one object, several variables

```java
int[] v = {1, 2, 3};
int[] w = v;
w[0] = 99;
// v[0] também é 99
```

`v` and `w` are **two slots** on the stack (or distinct fields), each with a **copy of the reference value** — and that value is **the same**: the array exists **once** on the heap.

This explains many bugs: you pass “the array” to a method and it mutates indices → the caller sees the change because there was only **one** heap structure.

---

## 5. Parameter passing: copy of the reference

Java is **always pass-by value**. If the parameter is a reference, the **value** copied is the **reference value** (like a copied “pointer”).

```java
void m(int[] a) {
    a[0] = -1;           // altera o objeto heap → chamador vê
    a = new int[]{9};    // só muda o parâmetro local `a`; chamador NÃO vê
}
```

Mental flow:

1. Caller has `int[] dados` with reference **R** to array **A**.
2. Calling `m(dados)` gives parameter `a` a **copy** of **R** → also points to **A**.
3. `a[0] = -1` mutates **A**.
4. `a = new int[]{9}` makes `a` point to **another** array; caller still has **R** → **A** unchanged by that reassignment (but may already have been mutated in step 3).

Exercised in [`ReferencesAndPointers.java`](ReferencesAndPointers.java).

---

## 6. `null`

`null` is the reference that points to **no** object. Dereferencing (`something.field`, `something.method()`) with `null` → `NullPointerException`.

Useful for “no object yet” or optional (modern code often prefers `Optional` for returns; `null` still appears in legacy APIs).

---

## 7. Arrays are heap objects

`int[]`, `Object[]`, … are **objects**. The variable is a reference; `.length` and indices live on that object. Multidimensional arrays in Java are **arrays of references** to other arrays (except the last level of primitives).

---

## 8. `final` and reference (link to `basics-language`)

`private final List<X> itens = new ArrayList<>();` → you cannot do `itens = otherList`; you **can** mutate the list **contents** with `add`, because the `ArrayList` object is the same. See [finalstaticinterfacesandencap-en.md](../../../../../../../../basics-language/src/main/java/com/estudos/basics/finalstaticinterfacesandencap-en.md) in the sibling module.

---

## 9. C vs Java (summary)

| Aspect | C (pointer) | Java (reference) |
|--------|-------------|------------------|
| Arithmetic | `p + 1` common | None |
| Raw address | `void*`, casts | Not exposed |
| Free memory | manual `free` | GC |
| Null reference | `NULL` | `null` |

More detail and diagrams: [memoryphysicaldeepdive-en.md](memoryphysicaldeepdive-en.md).

---

## 10. Typical reasoning mistakes

1. Thinking `==` on `String` compares text (it compares identity, except specific interned literals — topic in `VariablesAndTypes` / pool).
2. Thinking passing an object **copies** the whole object (only the reference is copied).
3. Thinking `final List` blocks `add` (it blocks **replacing** the list variable, not mutating the list object).
4. Confusing **two equal references** with **two `equals` objects** — essential for `HashMap` / `HashSet` (`core` → `EqualsHashCodeContract`).

---

## 11. Where to continue in this repo

| Destination | Topic |
|-------------|--------|
| [memoryandreferences-en.md](memoryandreferences-en.md) | Stack/heap, threads, interviews |
| `basics-language` → `VariablesAndTypes` | `==` vs `equals`, wrappers |
| `core` → `PassByValue` | `StringBuilder` and parameter reassignment |
| `core` → `jvmmemorymodelintro.md` | JVM depth relative to `basics` |
