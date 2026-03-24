**PT:** [sequencedcollectionsexample.md](sequencedcollectionsexample.md)

# `SequencedCollectionsExample`

Example: `SequencedCollectionsExample.java`.

## Goal

**`SequencedCollection`**: explicit order with `getFirst`, `getLast`, **`reversed()`** view without copying the whole list.

## Nuances

- `reversed()` is a view of the sequence — mutations may reflect depending on the implementation (see Javadoc).
- Available on lists/deques that implement the interface.
