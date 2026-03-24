**PT:** [multithreadingintro.md](multithreadingintro.md)

# `MultithreadingIntro`

Example in: `MultithreadingIntro.java`.

If variables, methods, `static`, objects, or arrays are weak, use the **`basics`** module in the repo first: [`basics/README-en.md`](../../../../../../../../../basics/README-en.md) (relative path from this `.md`). The rest of this text builds concurrency on top of that.

---

## 1. Mandatory prerequisites

### 1.1 Process and JVM (very short)

When you run a Java program, the operating system treats it as a **process**. Inside it is the **JVM**, which runs your bytecode. The JVM manages **memory** (among other things the **heap**, where objects and `static` variables shared by the application live).

### 1.2 What a **thread** is

A **thread** is a thread of execution: the sequence of instructions the CPU runs **as if** it were a linear story. You can have **several threads** in the same process; they share the same memory space (the same heap). So two threads can read and write the **same variable** — and that is where the problems in this document come from.

### 1.3 **Single-thread** (only one thread): the easy case

Imagine **only** the `main` method running, without `new Thread(...)`.

- The processor executes **one instruction at a time** (in your code, it looks like “everything in sequence”).
- If you do `int x = 0;` and then in a loop `x++` ten thousand times, in the end `x` is **always** 10_000. Nobody else touches `x` while your code runs.

**Key idea:** with **one** thread, there is no “other person” changing the same variable in the middle of your reasoning. The result is predictable.

### 1.4 Shared memory

In the example, `static int unsafe` and `static final AtomicInteger ATOMIC` live on the **heap** (tied to the class, but what matters: **one place in memory** visible to **all** threads).

- Thread A and thread B **see the same number** when they read `unsafe`.
- If both “add 1” without coordination, the final result may **not** be what you expect. That is not magic: it is **interference** between two instruction sequences.

---

## 2. Two threads: what changes mentally

When you do `t1.start()` and `t2.start()`, there are **two stories at once** in the same program.

- The OS / scheduler **switches** which thread runs in each time slice (and on multi-core machines, two threads may even run in parallel on different CPUs).
- You **do not** control the exact order: sometimes a bit of thread 1 runs, then 2, then 1 again… This is called **interleaving**.

**Important:** even when it looks “at the same time”, for your code’s logic what matters is: **between two of your lines, another thread may have executed a lot.**

---

## 3. What a **race condition** is

**Race condition** = the program’s result depends on the **order** threads are interleaved, and that order **is not guaranteed**. **Erratic** or **incorrect** behavior relative to what you want (e.g. a counter that should be 20_000 but comes out 19_847).

### 3.1 Why `unsafe++` is dangerous with two threads

In Java, `unsafe++` is **not** one indivisible operation on the hardware. Mentally, it is **three steps**:

1. **Read** the current value of `unsafe` (e.g. you read 100).
2. **Compute** 100 + 1 = 101.
3. **Write** 101 back to `unsafe`.

If only one thread does this, it is fine. If **two** do it “at the same time” (in the interleaved sense), this can happen:

| Moment | Thread 1 | Thread 2 | Actual value of `unsafe` |
|--------|-----------|----------|--------------------------|
| 1 | Reads 100 | | 100 |
| 2 | | Reads 100 | 100 |
| 3 | Writes 101 | | 101 |
| 4 | | Writes 101 | 101 |

Both incremented “in their heads”, but the value only went up **once** from the point where both read 100. **You lost an increment.** Repeated thousands of times, the total ends up **below** 20_000.

That is a **race condition** on the counter: both threads “race” to read and write without rules, and **updates are lost**.

### 3.2 Name in Portuguese

“Condição de corrida” = several threads “racing” for the same resource; depending on who reads or writes in between, the result changes.

---

## 4. Tie-in to code: Part 1 — `synchronized` and `LOCK`

- **`synchronized (LOCK) { unsafe++; }`** tells the JVM: **only one thread at a time** may execute this block **for this `LOCK` object**.
- While one thread is inside the block, the other **waits**. So the trio read → add → write **is not** interrupted by another thread doing the same on the same counter.

Therefore, with two threads × 10_000 increments, in the end `unsafe` is **20_000**.

The `LOCK` object is just a **lock** (in Java literature: a **monitor**). It does not have to be “the counter”; it must be **the same object** all threads use to synchronize.

---

## 5. Part 2 — `AtomicInteger` (another path)

`AtomicInteger` offers operations like **`incrementAndGet()`** that the JVM/hardware treat as **one unit** for concurrency (usually **compare-and-swap**, CAS: “if the value is still X, swap to X+1; otherwise retry”).

- You do not need to write `synchronized` around it for this simple counter.
- There is still **one** shared variable; the difference is the **increment primitive** is already built for multiple threads.

**Limitation:** this fixes **one** integer with atomic operations. If several fields must change **together** (“all or nothing”), you usually need **locks** or `java.util.concurrent` structures.

---

## 6. `join()`

`main` calls `t1.join()` and `t2.join()` to **wait** for those threads to finish **before** printing the counter.

Without `join()`, `main` could print **while** threads were still incrementing — you would see a number **in the middle**, not the final one.

---

## 7. `volatile` (so you do not confuse it)

**`volatile`** on a field helps with **visibility** (reads/writes are not “hidden” in a misleading way across threads). It does **not** make `count++` atomic: it is still read + add + write in separate steps. For this counter, **`volatile` alone does not fix it**.

---

## 8. Summary in study order

1. **One thread** → simple sequence; `++` on a variable is predictable.
2. **Several threads** + **same variable** → unpredictable interleaving.
3. **`unsafe++`** → three steps → **race condition** → wrong counts.
4. **`synchronized`** → mutual exclusion → one complete increment at a time.
5. **`AtomicInteger`** → increment built for concurrency.
6. **`join`** → wait for completion before reading the result.

## 9. `meuPlayground()`

In the `.java`, try **removing** `synchronized` from `incrementarSync` and run several times: the first print should **vary** below 20_000. That **is** a race condition in practice.
