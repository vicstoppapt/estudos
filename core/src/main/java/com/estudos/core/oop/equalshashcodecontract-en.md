**PT:** [equalshashcodecontract.md](equalshashcodecontract.md)

# `EqualsHashCodeContract`

Example in: `EqualsHashCodeContract.java` (nested class `Pessoa`).

## Goal

Show why **`equals` and `hashCode` together** are required for **`HashMap`** keys (and **`HashSet`** elements): locate the bucket, then **disambiguate collisions** with `equals`.

## Flow in `HashMap`

1. **`put(p, "dev")`** — computes **index/bucket** from `p`’s `hashCode()` (after the JVM’s internal mixing).
2. **`get(p)`** — same key **by reference** → same hash → same bucket → `equals` confirms → returns `"dev"`.
3. **`get(new Pessoa("Ana", 1))`** — **another object** on the heap:
   - If **`hashCode`** matches the original key’s **and** **`equals`** returns `true`, the map finds the entry.
   - If **`hashCode`** differs (common bug when only `equals` is overridden), `get` may go to the **wrong** bucket and return **`null`** even with “same content”.

## What `Pessoa` does right

- `equals` uses **`id` and `nome`** (with `Objects.equals` for `String`).
- `hashCode` uses **`Objects.hash(nome, id)`** — same fields.

## Anti-patterns (not in code, but the example exists to avoid them)

- **`equals` on mutable fields** — if the key changes after `put`, the map “loses” the entry.
- **Subclass that redefines `equals` without symmetry care** — Liskov in collections is tricky; prefer composition or clear docs.

## Relation to `ObjectMethods`

There the focus is the trio on a value type; see [objectmethods-en.md](objectmethods-en.md). Here the focus is **practical effect in `HashMap`**.
