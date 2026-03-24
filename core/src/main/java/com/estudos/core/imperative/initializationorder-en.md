**PT:** [initializationorder.md](initializationorder.md)

# `InitializationOrder`

Example in: `InitializationOrder.java` (includes static nested class `Sub`).

## Goal

Show the **actual order** of execution of **static blocks**, **instance blocks**, and **constructors** when there is **inheritance** and **first use** of a class.

## Rules summarized (mental model)

1. **Class loading** — before any `new` or static use of the class, the JVM loads the class. **`static { }`** blocks run **once** per load, in the order defined by the language (superclass before subclass in the static initialization chain).
2. **Instance** — when executing `new Sub()`:
   - The subclass constructor **implicitly** calls the super constructor (`super()`), unless there is an explicit `super(args)`.
   - **Before** the super constructor body run the super’s **instance blocks** and then the super constructor.
   - Then subclass instance blocks and subclass constructor.

## What the print numbering means

- **`1`** — static block of `InitializationOrder` when that class is loaded (before `main` finishes what it needs from the class).
- **`2`** — start of `main`.
- **`3`** — static block of `Sub` the first time `Sub` is used (here, in `new Sub()`).
- **`4`, `5`** — instance block and constructor of the **super** while constructing an instance of `Sub`.
- **`6`, `7`** — instance block and constructor of **`Sub`**.

The exact order you see in the output **confirms** the rule “super before sub” in instance construction and when `Sub`’s static blocks run (when class `Sub` is initialized).

## Nuances

- **Class order in `main`:** if you referenced only `Sub` first, statics still respect hierarchy (superclass initialized earlier in the sub’s linking process — fine detail depends on when each class loads; the current example is designed to match the prints commented in code).
- **Fields vs blocks:** field initializers and instance blocks run in **textual** order in the class, before the constructor.
- **Do not confuse** with order across **unrelated** classes — each tree follows its own rules.

For formal precision, cross-check the JLS (class initialization and instance creation).
