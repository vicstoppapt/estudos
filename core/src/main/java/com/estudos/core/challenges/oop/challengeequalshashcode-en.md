**PT:** [challengeequalshashcode.md](challengeequalshashcode.md)

# `ChallengeEqualsHashCode`

Solution in: `ChallengeEqualsHashCode.java`. Tests cover `get` with an equal key.

## Goal

Show that **`equals` without a coherent `hashCode`** breaks **`HashMap.get`** for “new” but semantically equal keys.

## What `Quebrado` does

- `equals` based on field `k`.
- `hashCode` delegates to `Objects.hash(k)` — **comment out** the method (or return a wrong constant) to see `map.get(busca)` return **`null`** even when `equals` would be true in the intended logic, because the map looks in the **wrong bucket**.

## Nuances

- Contract: equal → **same** `hashCode` (mandatory); the converse need not hold.
- **Mutability:** if the key changes after `put`, the map may “lose” the entry.
- Relates to `oop.EqualsHashCodeContract` and [equalshashcodecontract-en.md](../../../oop/equalshashcodecontract-en.md).

## Experiment

As suggested in the source Javadoc: comment `hashCode` and run `mvn test` or `main`.
