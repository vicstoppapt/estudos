# `VariablesAndTypes`

**PT:** [variablesandtypes.md](variablesandtypes.md)

Example: `VariablesAndTypes.java`.

**Physical memory, stack/heap, pass-by-value vs “reference”, comparison with other languages and ROM/RAM:** see [memoryandreferences-en.md](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/memoryandreferences-en.md) (`basics-memory`). **Pointers/references in depth:** [pointersreferencesdeepdive-en.md](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/pointersreferencesdeepdive-en.md).

This file gathers what is often missing when someone says “I know variables”: **what the JVM stores in the variable** when the type is primitive vs **object reference** (like `String`), and **why** non-primitive types exist.

---

## Where to read more in this repo

| Topic | Where |
|-------|--------|
| Passing `int` vs passing a reference into a method | `core` → `PassByValue` / `passbyvalue.md` |
| `String`, `==` vs `equals`, pool | `core` → `StringImmutability` / `stringimmutability.md` |
| Stack, heap, memory model (overview) | `core` → `JvmMemoryModelIntro` / `jvmmemorymodelintro.md` |
| RAM vs disk, references in interviews, other languages | **basics-memory** → [memoryandreferences-en.md](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/memoryandreferences-en.md) |
| Pointers, `==` vs identity, parameters | **basics-memory** → [pointersreferencesdeepdive-en.md](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/pointersreferencesdeepdive-en.md) · [`ReferencesAndPointers`](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/ReferencesAndPointers.java) |
| CPU cache vs browser, JVM does not “put” data in L1 | [cpucachejvmenavegador-en.md](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/cpucachejvmenavegador-en.md) |

---

## What a variable is (for Java)

A **name** in a method (**local** variable) or in a class (field) bound to a **value** the machine can read and write.

The important part: **the “shape” of that value depends on the type**:

- For a **primitive** type, the variable holds **the value itself** (the number, boolean, encoded character).
- For a **reference** type (any **class** or interface, including `String`, `Integer`, `List`, etc.), the variable holds **a reference** — mentally an “address” or pointer to an **object** living elsewhere (typically the **heap**).

Java does **not** expose references as numbers you can manipulate (unlike C). You only use the variable name; the compiler/JVM handle the rest.

---

## Primitives: list and idea

There are **eight** types in Java:

| Type | Example | Notes |
|------|---------|--------|
| `byte`, `short`, `int`, `long` | `int n = 3;` | integers of different sizes |
| `float`, `double` | `double x = 1.5;` | floating-point |
| `boolean` | `boolean ok = true;` | only `true` / `false` |
| `char` | `char c = 'A';` | one UTF-16 Unicode code unit (16 bits); **not** `String` |

**How Java “thinks”:** these values are **normal** for the CPU: they fit in registers or the method stack, and operations (`+`, `==`, etc.) are direct. **No** object header, no methods “on” the value — so you cannot do `5.toString()`; you use `Integer.toString(5)` or concatenation.

**Default** on class fields if you do not initialize: `0`, `false`, `'\u0000'`, depending on type.

---

## Types that are **not** primitive (reference / object)

Any **class** (including `String`, `Integer`, `Object`; arrays `int[]` are “reference” too though Java uses special syntax).

- A variable of type `String` does **not** contain the characters “inside it” in the same sense `int` holds the number’s bit pattern: it holds a **reference** to a `String` object on the heap where the content (and internal metadata) lives.
- **`null`** only makes sense for reference types: means “does not point to any object”.

**`String` specifically:** immutable class; literals `"ana"` are shortcuts to `String` objects. Compare content → **`equals`**, not `==` (except specific pool cases — see `stringimmutability.md`).

---

## Why do non-primitive types exist?

1. **Objects have behavior and structure:** `String` has `length()`, `substring()`, etc. A bare `int` has no methods; it is just a value.
2. **Collections and generic APIs:** `List<int>` **does not** exist in Java (until very recent primitive generics with special rules). Historically you use `List<Integer>` — **wrapper** — because generics work with **reference** types.
3. **Semantic nullability:** `Integer podeSerNull` can be `null` for “absent”; `int` cannot be `null` (use wrappers or `Optional` in modern APIs).
4. **Inheritance and polymorphism:** you can have `Object ref = "texto";` — a common supertype; primitives do not join that hierarchy (except **boxing**).

---

## Wrappers (`Integer`, `Double`, …)

Each primitive has a “box” type: `Integer` for `int`, `Double` for `double`, etc.

- They are **classes** → instances on the **heap** (except optimizations like **integer caching** for small values).
- **Autoboxing:** `Integer x = 5;` the compiler translates to something like `Integer.valueOf(5)`.
- **Unboxing:** `int n = x;` generates `x.intValue()` (and may throw `NullPointerException` if `x` is `null`).

They integrate primitives with object-oriented APIs (collections, `Object`, reflection, etc.).

---

## Quick mental model: stack vs heap (local)

- **Local** primitive variables: the value usually lives in the method **frame** (linked to **stack** per thread).
- **Local** reference variables: the **reference** sits in the frame; the **pointed-to object** is on the **heap**.

This is simplified; the JIT may optimize. For precision and threads, `jvmmemorymodelintro.md` in `core`.

---

## `final`

`final int limite = 10;` — after initialization, **you cannot** reassign `limite`. The object referenced by a `final` variable **may** change **internal** state if the object is mutable; you just cannot make the variable point to **another** object (`final String s` cannot receive another `String`, but `String` is immutable anyway).

---

## Next step in the `basics` module

`methodsandparameters-en.md` — how methods receive primitives vs references (then `core` → `passbyvalue.md` goes deeper).
