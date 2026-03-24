**PT:** [README.md](README.md)

# Core — Java for interviews and how the language works

**`estudos` repo:** [DOCUMENTATION.md](../DOCUMENTATION.md) (how to run all modules) · [GLOSSARY.md](../GLOSSARY.md) (PT↔EN terms).

Goal: **common interview questions** and **how the JVM/language behave**. Packages group topics (`imperative`, `oop`, …); the **study order** below is the reference (from **easier, less critical** content to **harder and more important** day-to-day / senior interviews).

**Optional prerequisite:** if `String`, loops, `new`, `static`, or arrays are weak, go through the **`basics`** module first (sibling folder under `estudos/basics/README.md`).

## Suggested study order

| # | Class (`exec:mainClass`) | Why this order |
|---|--------------------------|----------------|
| 1 | `com.estudos.core.imperative.StringImmutability` | Concrete; `String`, `==` vs `equals`, pool — few prerequisites. |
| 2 | `com.estudos.core.imperative.PassByValue` | Foundation for parameters (primitive vs reference); shows up often in interviews. |
| 3 | `com.estudos.core.imperative.InitializationOrder` | Language detail; common on exams, **less** in everyday work. |
| 4 | `com.estudos.core.exceptions.ExceptionsHierarchy` | Checked vs unchecked, `finally` — straightforward read. |
| 5 | `com.estudos.core.oop.ObjectMethods` | `toString`, `equals`, `hashCode` in practice before the formal contract. |
| 6 | `com.estudos.core.oop.AbstractVsInterface` | OOP modeling; simple inheritance + interfaces. |
| 7 | `com.estudos.core.generics_collections.GenericsBasics` | Erasure, `List<?>` — abstract but needed for APIs. |
| 8 | `com.estudos.core.generics_collections.CollectionsOverview` | `List` / `Set` / `Map` day-to-day; high-level complexity. |
| 9 | `com.estudos.core.oop.EqualsHashCodeContract` | **Heavily tested:** `HashMap` / `HashSet` and the contract. |
| 10 | `com.estudos.core.oop.ComparableComparator` | Sorting; `Comparable` / `Comparator` APIs. |
| 11 | `com.estudos.core.declarative.DeclarativeVsImperative` | Assumes some lambda/stream fluency (review the **java8** module if stuck). |
| 12 | `com.estudos.core.jvm.JvmMemoryModelIntro` | Stack, heap, class loading — mental model for the rest. |
| 13 | `com.estudos.core.jvm.GarbageCollectorBasics` | GC from an interview angle; complements the previous item. |
| 14 | `com.estudos.core.concurrency.MultithreadingIntro` | **Hardest** (race, `synchronized`, `AtomicInteger`); **critical** in concurrent backend. |

### Challenges (after related examples)

| Order | Class | After studying… |
|-------|-------|-----------------|
| 1 | `challenges.collections.ChallengeAnagram` | `CollectionsOverview`, `GenericsBasics` |
| 2 | `challenges.oop.ChallengeEqualsHashCode` | `EqualsHashCodeContract` |
| 3 | `challenges.concurrency.ChallengeThreadSafeCounter` | `MultithreadingIntro` |

---

## `package-info.java` — what it is

A Java file whose only job is to hold **package documentation** (the package name at the top must match the folder). Tools like **JavaDoc** use this text as the package “overview” page. It is not a class with `main`; other classes do not need to import it.

**Text per topic:** each package has a **`README.md`** (index / overview). Several example packages also have **one `.md` per class** in the same directory (e.g. `generics_collections/genericsbasics.md` ↔ `GenericsBasics.java`) with nuances and longer theory; the `.java` file keeps flow comments.

**Comments in `.java`:** explain **what the code does** (fields, blocks, calls like `join`) in a programmer style. **Concepts and nuances** live in the package **`README.md` index** and in **per-class `.md` files** (when they exist); do not mix HTML or long tutorials into source.

## Your playground in each example

Empty **`meuPlayground()`** method + **`// meuPlayground();`** in `main`. Your experiment; not the challenge in `challenges.*`.

## Structure by package (topic, not order)

| Package | Paradigm / focus | Classes |
|---------|------------------|---------|
| `com.estudos.core.imperative` | Imperative: steps, state, base types | `StringImmutability`, `PassByValue`, `InitializationOrder` |
| `com.estudos.core.exceptions` | Errors and exception flow | `ExceptionsHierarchy` |
| `com.estudos.core.oop` | OOP: contracts, inheritance, identity | `ObjectMethods`, `AbstractVsInterface`, `EqualsHashCodeContract`, `ComparableComparator` |
| `com.estudos.core.generics_collections` | Generics + collections | `GenericsBasics`, `CollectionsOverview` |
| `com.estudos.core.declarative` | Declarative style (streams) | `DeclarativeVsImperative` |
| `com.estudos.core.jvm` | JVM: memory, GC | `JvmMemoryModelIntro`, `GarbageCollectorBasics` |
| `com.estudos.core.concurrency` | Threads and shared memory | `MultithreadingIntro` |

## Quick map (lookup by question)

| Question | Where to look |
|----------|---------------|
| `==` vs `equals` on `String` | `imperative.StringImmutability` |
| Is Java pass-by-reference? | `imperative.PassByValue` |
| Primitive vs reference / where data lives | `jvm.JvmMemoryModelIntro` |
| Checked vs unchecked | `exceptions.ExceptionsHierarchy` |
| `equals`/`hashCode` and `HashMap` | `oop.EqualsHashCodeContract` |
| `synchronized` / safe counter | `concurrency.MultithreadingIntro` |
| What is the garbage collector? | `jvm.GarbageCollectorBasics` |
| Imperative vs stream | `declarative.DeclarativeVsImperative` |

## Automated tests

```bash
mvn test
```

- **JUnit 5** + **AssertJ** (fluent assertions) in `challenges.*`.
- **Mockito** in `src/test/java/com/estudos/core/mockito/RelogioMockitoDemoTest.java` (isolated example).

## Run examples

```bash
cd core
mvn -q compile exec:java "-Dexec.mainClass=com.estudos.core.imperative.StringImmutability"
```

**PowerShell:** keep quotes on `-Dexec.mainClass=...`.

## Challenges (`com.estudos.core.challenges.*`)

Challenge source includes **reference solutions** so `mvn test` passes (e.g. comment `Quebrado#hashCode` in `ChallengeEqualsHashCode` to reproduce the bug described in the Javadoc).

| Package | Class | Skill |
|---------|-------|-------|
| `challenges.collections` | `ChallengeAnagram` | letters / sorting |
| `challenges.oop` | `ChallengeEqualsHashCode` | contract in `HashMap` |
| `challenges.concurrency` | `ChallengeThreadSafeCounter` | `synchronized` / `AtomicInteger` |

## Further reading

- [Java Language Specification](https://docs.oracle.com/javase/specs/jls/se17/html/)
- [JVM Specification](https://docs.oracle.com/javase/specs/jvms/se17/html/)
