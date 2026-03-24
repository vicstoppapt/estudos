**PT:** [collectionsoverview.md](collectionsoverview.md)

# `CollectionsOverview`

Example in: `CollectionsOverview.java`.

## Goal

Provide a **mental map** of the most used Collections Framework families: **list ordered by position**, **set without duplicates (in the `equals` sense)**, **key→value map**, **queue/deque** — and contrast **Hash** vs **Linked** vs **Tree** where the example touches them.

## By block in `main`

### `ArrayList` (`List`)

- **Semantics:** sequence with explicit **insertion order**, index access, **duplicates allowed**.
- **Typical cost:** index access O(1); add/remove at end amortized O(1); in the middle can cost O(n) from shifting elements.
- **Nuance:** “order” here is the order you `add`ed, not sorting by a criterion (for that you would use `sort` or `TreeSet`/`TreeMap`).

### `HashSet` (`Set`)

- **Duplicate `add`:** second `add("x")` does not increase `size()` — the set uses element `equals`/`hashCode` for equality.
- **Iteration order:** in `HashSet` it is **not** insertion order nor natural ordering; it is defined by the internal implementation (and may change if capacity/rehash changes). For predictable order, use `LinkedHashSet` or `TreeSet`.

### `HashMap` (`Map`)

- **Role:** **key → value** association; unique keys in the map (replaces value if same key `equals`).
- **Typical cost:** `get`/`put` average O(1), depending on well-distributed `hashCode` and table size.

### `LinkedHashMap`

- **Difference from `HashMap`:** keeps **insertion order** (or access order, if supported by the constructor) on **iterations** over entries/keys/values.
- **When it matters:** stable order for logs, simple caches, readable serialization, etc.

### `TreeMap`

- **Keys sorted** by the key type’s **natural order** (`Comparable`) or a **`Comparator`** passed to the constructor.
- **Cost:** operations O(log n). Does not allow `null` key (unlike `HashMap`, which allows one `null` key).

### `ArrayDeque` as `Queue`

- **`poll()`** removes and returns the **first** in the queue (FIFO behavior in the shown use).
- **Nuance:** `ArrayDeque` is generally preferred over legacy `Stack` for stack/queue; here it only illustrates a simple queue.

## Comparing “Hash / Linked / Tree” in one sentence

| Idea | Hash | Linked (Hash…) | Tree |
|------|------|----------------|------|
| Iteration order | does not guarantee insertion/natural | insertion (LinkedHash*) | ordered by key/element |
| Typical cost | O(1) average map/set | a bit more overhead | O(log n) |

## Limitations of this example

Only instantiates and prints; does not show **thread-safety** (`ConcurrentHashMap`, etc.), iterator **fail-fast**, or `NavigableMap`/`SortedSet`. It serves as an anchor for the official interface docs (`List`, `Set`, `Map`, `Queue`).
