**PT:** [comparablecomparator.md](comparablecomparator.md)

# `ComparableComparator`

Example in: `ComparableComparator.java` (`record Item`).

## Goal

Separate the type’s **natural order** (`Comparable<T>`) from **ad hoc orders** (`Comparator<T>`) without permanently changing the class/type.

## `Item` and `Comparable<Item>`

- **`compareTo`** implements the type’s **default** order: here **ascending price** (`Integer.compare(this.preco, o.preco)`).
- **Return semantics:** negative/zero/positive as in the `Comparable` contract (same as `Comparator`).

## `itens.sort(null)`

- **`null`** as `Comparator` in `List.sort` means: use elements’ **natural order** — i.e. delegate to **`Comparable`** on `Item`.
- Result: sort by **price** (`10` before `20`).

## `itens.sort(Comparator.comparing(Item::nome))`

- **New rule for this call only:** sort by **`nome`** lexicographically (`String` natural order).
- **Does not remove** `Comparable`; it only **overrides** ordering for this operation.
- After this line, the list is ordered by name; the example prints both steps for comparison.

## Nuances

- **Stability:** `TimSort` (used by `List.sort`) is **stable**: ties keep previous relative order — useful when chaining sorts by secondary criteria (`thenComparing`).
- **Nulls:** `Comparator.nullsFirst` / `nullsLast` when elements may be `null`.
- **Records:** generate accessors `nome()`, `preco()` — hence `Item::nome` works as an extractor.

## Mental extension

To sort by price with tie-break by name: `Comparator.comparing(Item::preco).thenComparing(Item::nome)`.
