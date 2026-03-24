# What is a framework?

**`frameworks` aggregator** (several Maven submodules). Depth levels: [niveisdefinicao-en.md](niveisdefinicao-en.md).

**PT:** [oqueeumframework.md](oqueeumframework.md)

---

## 1. Framework vs library

| | **Library** | **Framework** |
|---|-------------|-----------------|
| Who drives the flow | **You** call library functions when you want | The **framework** calls your code via **hooks** (*callbacks*, annotations, interfaces) |
| Java example | `Jackson.readValue(...)`, `java.net.http` | **Spring**, **Jakarta EE** (server calls *servlets*), **JUnit** (runs your `@Test`) |

With a framework: **inversion of control** (IoC): the **runtime** orchestrates; you plug in **extensions**.

---

## 2. What it is for in practice

- **Less repeated infrastructure** code (HTTP, JSON serialization, transactions, queues).
- **Shared conventions** across the team (folders, names, annotations).
- **Ecosystem** (*starters*, docs, tooling).

Cost: **learning curve**, **version** coupling, **magic** if you do not know what the default does.

---

## 3. Why Spring Boot as the model in this module

- **Widely used** in Java companies (APIs, *batch*, integrations).
- **Spring Boot** = Spring + **auto-configuration** + **embedded server** + Maven **starters** — a good case study of an **opinionated** framework with clear **extension points**.

It is not always the best choice — see [outrosframeworksjvm-en.md](outrosframeworksjvm-en.md).

Next: [injecaodependencias-en.md](injecaodependencias-en.md) → [mvcerestcamadas-en.md](mvcerestcamadas-en.md) (MVC REST and layers in code).
