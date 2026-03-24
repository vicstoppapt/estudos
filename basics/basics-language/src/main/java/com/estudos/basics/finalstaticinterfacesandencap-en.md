# `final`, `static`, `interface`, and encapsulation

**PT:** [finalstaticinterfacesandencap.md](finalstaticinterfacesandencap.md)

Text tying together four common **Java modeling** pieces. Code: [`FinalStaticInterfacesAndEncap.java`](FinalStaticInterfacesAndEncap.java). For **`static` in detail**, see [`StaticMembers.java`](StaticMembers.java) and [staticmembers-en.md](staticmembers-en.md). Objects/`private` basics: [objectsconstructorsthis-en.md](objectsconstructorsthis-en.md). **Reference vs object (what “pointer” means in Java):** [pointersreferencesdeepdive-en.md](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/pointersreferencesdeepdive-en.md) in the `basics-memory` submodule.

---

## 1. `final`

### 1.1 `final` class

`public final class X` → **nobody can** `extends X`. The JDK uses this for `String`, `Integer`, etc.: the API **fixes** class behavior; you **consume** methods, not specialize via inheritance.

### 1.2 `final` method

In a **non**-`final` class, a `final` method **cannot** be overridden in subclasses. Useful to lock invariants or performance (the JIT may optimize non-virtual calls in some cases).

### 1.3 `final` field (instance or `static`)

- **Instance:** must be assigned **once** per constructor (or in a field initializer). After that, the **reference** (if reference type) does not change; the **pointed-to object** may be mutable (`final List` → you can `add`, but the variable cannot point to another list).
- **`static final`:** **class** constant (e.g. `MODULO` in the example). Convention: `UPPER_SNAKE_CASE`.

#### 1.3.1 `final List` and a growing `ArrayList`

`final` applies to the **field** (the variable holding the reference), not the **inside** of the object.

- `private final List<String> itens = new ArrayList<>();` → you **cannot** do `itens = new ArrayList<>()` later (another list).
- You **can** `itens.add("x")` as long as memory allows: you mutate the **same** `ArrayList` object.

Under the hood, `ArrayList` may **reallocate** an internal array (`elementData`) when full — copies elements to a larger array. That is **inside** the class implementation; the Java `ArrayList` object your variable refers to **keeps the same identity** (same object “address” on the heap). So there is **no** contradiction between “list grows” and `final` reference.

Analogy: `final` fixes the **label** (“always this house”); inside the house you can **furnish and extend**. Practical size limits: memory and `int` for indices/size in standard collections — not imposed by `final`.

**Code:** `CarrinhoDemonstracao` in [`FinalStaticInterfacesAndEncap.java`](FinalStaticInterfacesAndEncap.java) — `identidadeDaLista()` before and after many `add`s: the `identityHashCode` of the **same** `itens` reference stays equal.

#### 1.3.2 Multiple constructors: different initial values, no change afterward

Each constructor must assign **once** every `final` field on its execution path. **Different instances** may end up with **different values** (e.g. `new PedidoLogico(1L)` vs `new PedidoLogico(2L, instanteFixo)`).

After `new` completes, you **cannot** change that `final` field on that object — not “rename later”; it **freezes** identity/creation time **per instance** after construction.

**Code:** `PedidoLogico` (two constructors) in the same `.java`.

#### 1.3.3 Concrete examples (application logic, not just “name”)

| Idea | Why `final` helps |
|------|-------------------|
| **Order / tenant ID** | Stable identity; avoids accidental reassignment in long flows (logs, events, `Map`). |
| **Creation `Instant`** | Immutable audit fact after building the entity. |
| **Injected dependency** (`Gateway`, `Clock`) | Same service/clock for the object’s whole lifetime — tests with a fixed mock, no mid-life reference swap. |
| **`final List` + mutate only with `add`/`remove`** | Whoever holds the list reference does not get the list “replaced” by another empty instance by mistake. |

**Code:** `ProcessadorFake` with fixed `Runnable gateway` in the example.

#### 1.3.4 Pros and cons

**Pros:** state or dependencies **guaranteed** after construction; fewer reassignment bugs; in concurrency, a properly published `final` reference is a useful pillar of the Java memory model; clearer code (“this does not change”).

**Cons:** if you need to **swap** the value during the object’s lifetime, you cannot — new object or refactor; multiple constructors require discipline (every path assigns all `final`s); many `final` fields can complicate builders/tests without helper patterns.

### 1.4 `final` local variable and parameter

- **Local:** cannot be reassigned after first assignment. Useful for readability and for use in lambdas/anonymous inner classes (in older versions sometimes required).
- **Parameter:** inside the method you cannot do `param = otherValue`. Nothing stops **mutating** the referenced object if it is mutable.

---

## 2. `static` (recap)

- Belongs to the **class**, not a concrete instance.
- `static` field → **one** shared storage for all instances (if any).
- `static` method → no `this`; only direct access to other `static` members of the same class (for instance members you need an explicit reference).

Details and example counter: [staticmembers-en.md](staticmembers-en.md).

---

## 3. `interface`

- **Contract:** list of behaviors (methods) a type **commits** to implement.
- Class uses **`implements InterfaceName`**; can implement **several** interfaces.
- Since Java 8: **`default`** method on the interface — base implementation; class may **override**. **`static`** method on the interface — called as `InterfaceName.method()`, not inherited to implementing class as instance methods.
- **Quick contrast with `abstract class`:** interface is mostly **capability**; abstract class can carry **state** and constructors. Both enable polymorphism via supertype.

In the example, `Reprodutor` requires `play`; `playSilencioso` is `default`.

---

## 4. Encapsulation

- Hide **internal state** (`private` fields) and expose only **operations** (`public` methods) that uphold **rules** (invariants).
- E.g. `ContaSimples` does not expose `centavos` directly; only `depositar` (with validation) and balance read. That way nobody does `centavos = -999` outside the class.
- **Getter/setter** are not ends in themselves: only make sense if they **control** access or are part of a contract (beans, APIs). See also [objectsconstructorsthis-en.md](objectsconstructorsthis-en.md).

---

## 5. API: producer vs consumer (link)

- **Consumer:** you use `String`, `List`, `Reprodutor` as declared types and call methods — you need not publish anything.
- **Producer:** you define `interface Reprodutor` or a `public` class with a stable API; other modules depend on that contract.
- You can be **both** in one project (define interface in one package, implement in another).

More context: [apiconceitogeral-en.md](apiconceitogeral-en.md).

---

## 6. Summary

| Piece | Core idea |
|-------|-----------|
| `final` (class) | No subclasses |
| `final` (field/local/param) | No variable reassignment; pointed-to object may be mutable |
| `static` | Class member, not per-instance |
| `interface` | Method contract; `implements`; `default`/`static` since Java 8 |
| Encapsulation | `private` + methods that enforce rules |
