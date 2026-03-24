# API — general IT concept (not just web)

**PT:** [apiconceitogeral.md](apiconceitogeral.md)

Cross-cutting text (no dedicated `.java`). Aligns vocabulary: when someone says **“there is no Java API for X”** (e.g. forcing data into CPU L1), **API** here means **exposed programming interface** — not necessarily HTTP.

---

## 1. What **API** means

**API** = *Application Programming Interface* → **application programming interface**.

In simple terms: a **contract** (explicit or implicit) stating **how** one piece of software **may** use another — **without** knowing all of that other piece’s internal details.

- **Who provides** the API exposes “public” operations (functions, endpoints, classes, syscalls…).  
- **Who consumes** writes code or requests **on top** of that contract.  
- If internals change but the contract stays the same, the consumer **keeps** working (ideally).

“Interface” in the name is **boundary between systems or layers**, not only “graphical UI”.

### 1.1 Producer and consumer

You can be **both**, across codebases or projects:

- **Consumer:** you call `String.isBlank()`, use `ArrayList`, send HTTP requests to a service — you use the contract someone else exposed.
- **Producer:** you publish a library, expose `public` classes or endpoints; or define an `interface` / contract that others implement.

`String` is `final`: in practice you are only a **consumer** of the class API (you do not extend it by inheritance). With **`List`**, the JDK defines the contract; you can be **consumer** (`new ArrayList<>()`) or **producer** (`MyList implements List`). See also [finalstaticinterfacesandencap-en.md](finalstaticinterfacesandencap-en.md).

---

## 2. Why you associate API with **web**

In day-to-day projects, **REST / HTTP API** is the most visible example:

- URLs, `GET`/`POST` methods, JSON, OpenAPI/Swagger, token authentication…

That **is** an API — but it is **one kind**: **remote**, **over the network**, often documented as the service’s “public” API.

The same **contract** idea applies **outside** the web.

---

## 3. API examples across IT contexts

| Context | What the “API” is | Example |
|----------|------------------|---------|
| **Language / standard library** | **Public** classes and methods the official documentation guarantees | In Java: `List.add`, `Files.readString`, `String.isBlank` — people say **“Java API”** or **“JDK API”** |
| **Framework** | What the framework **lets** you call (hooks, annotations, interfaces to implement) | Spring: `@RestController`, `JpaRepository`; Android SDK |
| **Operating system** | Calls that request kernel services (**syscalls**) or libraries wrapping them | **POSIX**, **Win32 API** — open file, create process, sockets |
| **Database** | How you send queries and receive results | **JDBC** (Java), **wire** protocol of Postgres/MySQL |
| **Hardware / firmware** | When it exists: registers, documented special instructions | Drivers, BIOS exposing certain calls; **much** CPU microarchitecture has **no** application-level API |
| **Network (beyond REST)** | Contracts in other protocols | **gRPC**, **AMQP** queues, **WebSockets** with agreed messages |
| **CLI** | Informal “API”: stable arguments and exit codes | `git`, `kubectl` — automation treats CLI as a contract |
| **Native library (JNI)** | Layer between Java and C/C++ | `native` signatures + `.so`/`.dll` — contract between JVM and native code |

In all these cases: **there is a documented boundary** between “what you may request” and “how the other side responds”.

---

## 4. Tie-in with “there is no API for L1”

The L1 cache is managed by CPU **hardware**. For a **Java application programmer**, there is **no**:

- `Cache.putInL1(...)` method in the JDK;  
- standard syscall to “pin a line in L1” on Windows/Linux for normal apps.

So: **no stable**, **supported** programming interface at that level — hence the colloquial **“no API”**.  
It does not mean “no software on Earth talks to the CPU”; it means **“there is no public contract for your app to use”** (what exists is microcode / opaque silicon).

---

## 5. API vs implementation

- **API** = what is **promised** (documentation, public signatures).  
- **Implementation** = **how** it is done underneath (may change as long as the contract holds).  

Example: `List` in Java is an **interface** (a kind of API); `ArrayList` and `LinkedList` are different **implementations**.

---

## 6. Public vs internal API

- **Public** (or “external”): other teams or third parties depend on it — changes are **versioned** and communicated.  
- **Internal**: only used inside the same product — you can refactor more freely.

---

## 7. One-sentence summary

**API** = **official, agreed way for one program to use another service, library, OS, or remote service** — **web** is just **one** very common category.

---

## 8. Where this crosses the rest of this repo

- [cpucachejvmenavegador-en.md](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/cpucachejvmenavegador-en.md) — where we discuss “no API” for L1.  
- [finalstaticinterfacesandencap-en.md](finalstaticinterfacesandencap-en.md) — `interface` as contract; producer vs consumer in code.  
- Java code: every `public` method/class you use from the JDK is part of the **Java SE API** (documented in [Oracle Docs](https://docs.oracle.com/en/java/javase/) or equivalent).
