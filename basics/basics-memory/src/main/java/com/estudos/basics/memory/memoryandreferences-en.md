# Memory, RAM, and references (interview and architecture review)

**PT:** [memoryandreferences.md](memoryandreferences.md)

Text **cross-cutting** the `basics` module: no dedicated `.java`; ties to `variablesandtypes-en.md`, `core/jvm`, and `core/imperative/PassByValue`.

Goal: recall (or learn) **where data lives**, **what RAM vs disk means**, **what “reference” means**, **what the JVM does “behind the scenes”** regarding “pass by reference”, and **how other languages** often differ — typical interview language and computer systems / architecture courses.

---

## 1. “Memory” in practice: not all the same

When you program, you almost always use the computer’s **memory hierarchy**:

| Level | What it is | Speed | Volatile? |
|-------|------------|-------|-----------|
| **CPU registers** | Small cells inside the processor | Extremely fast | Yes |
| **Cache (L1, L2, L3)** | Very fast memory between CPU and RAM | Very fast | Yes |
| **RAM** | Main memory where the OS and running processes load code and data | Fast (vs disk) | **Yes — volatile** |
| **Disk (SSD/HDD)** | Persistent storage: files, JDK install, etc. | Slow vs RAM | **No** (survives power loss) |

**Volatile** = when you power off, typical **RAM** content is lost. Running program, variables, JVM objects — conceptually tied to **RAM** and **virtual memory** the OS maps to RAM (and sometimes **swap** on disk when RAM is tight). **PC hardware, frequencies, swap in depth, limits, loops, pointers:** [memoryphysicaldeepdive-en.md](memoryphysicaldeepdive-en.md).

### 1.1 ROM — do not confuse with “disk”

**ROM** (Read-Only Memory), on a modern PC, is mainly **firmware** (e.g. UEFI/BIOS): factory-fixed or rarely updated, **not** where the Java app writes day-to-day data.

In informal talk, people sometimes say “ROM” when they mean **permanent storage**. For application programming:

- **RAM impact** all the time (heap, stack, loaded process code).
- **Disk impact** when there is I/O (files, on-disk DB, logs), when the OS uses **swap**, or when you install/update software.
- **ROM** as firmware: a normal app **does not rewrite** it — another domain (embedded, BIOS updates, etc.).

Short interview line: *“The running program works mainly in **RAM** (and caches). **Persistence** is disk. **ROM** on a PC is another topic (firmware).”*

---

## 2. Process, JVM, and your program

When you run `java ...`, the OS creates a **process**. The **JVM** is that process (simplifying): the OS gives that process **virtual address space** — pages that may sit in **physical RAM** or temporarily in **swap**.

Inside that space, the JVM manages:

- **Metaspace** (Java 8+, class metadata — not the old “PermGen” on the classic heap),
- **Heap** — where **most objects** live (instances, arrays, `String`, etc.),
- **Stack** — **per thread**: each method call has a **frame** with local primitives and **references** (pointers) to heap objects,
- **PC register, native stack** for JNI, etc.

This aligns with `core` → `JvmMemoryModelIntro` / `jvmmemorymodelintro.md` (short text in the project).

---

## 3. What a “reference” is (general concept)

A **reference** is a **value** that **identifies** where an object lives in JVM-managed memory — in interview terms you can say **“like a pointer, but not arithmetic”**: in Java you **cannot** do `ref + 1` to hop memory like in C.

- Variable of type `String s` → `s` holds a reference; the string **content** is in the **object** on the **heap**.
- Two variables may hold **the same reference** (same object) or **different** references to **equal** content (`equals`).

---

## 4. Java: “pass by reference” or not?

### 4.1 What the specification says

Java always passes arguments **by value** (*pass-by value*):

- If the argument is **`int`**, the copied value is the **number**.
- If the argument is **`String s`**, the copied value is the **reference value** (a logical “address”) — **not** the whole object.

So in an interview:

- **Correct:** *“Java is **pass-by value**. For reference types, the value passed is a **copy of the reference**.”*
- **Imprecise:** *“Objects are passed by reference.”* — confuses people who think the caller’s variable can be **reassigned** in the callee and reflect in the caller (it cannot, as in `PassByValue`).

### 4.2 What the JVM does “behind the scenes”

The JVM implements this with **real pointers** (or **compressed oops**, etc.) in HotSpot — **implementation detail**. The **language model** that matters for the programmer is:

- Copy the **reference bits** or **primitive bits**.
- The heap object is **not duplicated** just because you pass the reference as an argument — but the **parameter variable** is a **new local** with a **copy** of that pointer.

**Analogy:** you give someone your house **address** on paper. They have a **copy** of the address; if they **cross out the paper** and write another address, **your** original sheet **does not change**. If they enter the house and move the sofa, **you see the change** because it is the **same** house — that is **mutating the shared object**.

---

## 5. Stack vs heap (mental model for interviews)

| | **Stack (per thread)** | **Heap** |
|---|------------------------|----------|
| **What** | Method frames; local primitives; variables holding **references** | Objects and arrays (in practice) |
| **Who cleans** | When the method returns, the frame goes away | **Garbage collector** removes unreachable objects |
| **Sharing across threads** | Each thread has its own stack | **Shared heap** — concurrency needs rules (`synchronized`, etc.) |

**Note:** some objects may be **stack-allocated** by optimization (**escape analysis**) — JVM optimization, **does not** change the usual interview mental model.

---

## 6. Other languages — useful comparisons

### 6.1 C / C++

- **Explicit pointers:** arithmetic, `*p`, etc.
- **C++** has **references** (`int& x`) as **aliases** — **different** semantics from Java: you can have real pass-by-reference of a variable in C++.

### 6.2 Python (mental model)

Names are **bindings** to objects; mutable objects may be shared. Feels like “reference” as in Java; passing semantics are **similar** to passing “the reference value” (CPython implementation nuances).

### 6.3 C#

- **`ref` / `out`** allow explicit **pass-by-reference** of a variable — Java has **no** direct equivalent for parameters.

### 6.4 Rust

- **Ownership** and **borrowing** (`&T`, `&mut T`) — **very** different model; no GC on the heap like Java.

For **Java-only** interviews: master **pass-by value + copy of reference** and **stack/heap**; short, accurate comparisons with C++ or C# help if you are precise.

---

## 7. Full example (mental or in the IDE) — Java

```java
void exemplo(String a, int b) {
    a = "outro";   // só muda a cópia local da referência; quem chamou não vê mudança no SEU ponteiro
    b = 99;        // só muda a cópia local do int
}

void mutar(StringBuilder sb) {
    sb.append("!"); // muta o objeto no heap — quem chamou vê, porque ainda aponta ao MESMO objeto
}
```

Implemented and commented in `core` → `PassByValue.java`.

---

## 8. Interview one-liners

1. *“Primitives live as values in the frame; objects live on the **heap** and the variable holds a **reference**.”*
2. *“Java is **always pass-by value**; for objects, the value is the **reference**.”*
3. *“**RAM** is volatile for execution; **disk** for persistence; **ROM** on a PC is not the typical app target.”*
4. *“**Heap** is shared across threads; **stack** is per thread.”*

---

## 9. Where to go deeper in this repo

| File / module | Topic |
|---------------|--------|
| `basics-language` → [variablesandtypes-en.md](../../../../../../../../basics-language/src/main/java/com/estudos/basics/variablesandtypes-en.md) | Primitive vs `String` / `Integer`, wrappers |
| `core` → `passbyvalue.md` | Three scenarios with `StringBuilder` and `int` |
| `core` → `jvmmemorymodelintro.md` | Textual stack/heap reminder |
| `core` → `multithreadingintro.md` | Why shared heap needs synchronization |
| `memoryphysicaldeepdive-en.md` | Where RAM/CPU/disk sit in the chassis, swap, thrashing, limits, infinite loop, C vs Java pointers |
| `cpucachejvmenavegador-en.md` | CPU cache vs browser; RAM/heap vs cache; what goes into L1/L2/L3; not forcing via JVM |
| [pointersreferencesdeepdive-en.md](pointersreferencesdeepdive-en.md) | Reference vs identity, sharing, parameters, `null`, C vs Java; [`ReferencesAndPointers.java`](ReferencesAndPointers.java) |
| `basics-os-concurrency` → [processosthreadsecpu.md](../../../../../../../../basics-os-concurrency/src/main/java/com/estudos/basics/os/processosthreadsecpu.md) | OS process, threads, cores, context switch |
| `basics-networking` → [camadasetcpudp-en.md](../../../../../../../../basics-networking/src/main/java/com/estudos/basics/network/camadasetcpudp-en.md) | TCP/UDP, DNS, ports; then HTTP/gRPC in other `.md` in the same package |
| `basics-security` → [autenticacaoautorizacao-en.md](../../../../../../../../basics-security/src/main/java/com/estudos/basics/security/autenticacaoautorizacao-en.md) | AuthN/Z, JWT/OAuth at a high level, OWASP |
| `basics-security` → [injecaosqleddos-en.md](../../../../../../../../basics-security/src/main/java/com/estudos/basics/security/injecaosqleddos-en.md) | SQLi, DDoS, defense in layers; [engenhariasocialgolpes-en.md](../../../../../../../../basics-security/src/main/java/com/estudos/basics/security/engenhariasocialgolpes-en.md) |
| `basics-storage` → [modelosdedados-en.md](../../../../../../../../basics-storage/src/main/java/com/estudos/basics/storage/modelosdedados-en.md) | SQL vs NoSQL, ACID/CAP, JDBC, object storage |

---

## 10. RAM “does not last” — and performance?

When the program ends, the OS **reclaims** the process memory. During execution, **RAM pressure** can mean more frequent **GC** (Java) or **swap** (slow). **Profiling** (JFR, VisualVM) ties to real work — outside the minimum scope of this text, but knowing it **exists** already helps in senior interviews.
