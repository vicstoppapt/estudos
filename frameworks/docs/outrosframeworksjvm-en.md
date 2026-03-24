# Other JVM frameworks and runtimes (beyond Spring Boot)

**`frameworks` aggregator**. Spring as reference: [springbootniveis-en.md](springbootniveis-en.md). This repo also has parallel samples: **`frameworks-micronaut`** (Netty, Serde, validation) and **`frameworks-quarkus`** (REST/JAX-RS, CDI) — see [`README.md`](../README.md).

**PT:** [outrosframeworksjvm.md](outrosframeworksjvm.md)

This list is **indicative** — products evolve; compare **startup**, **memory**, **ecosystem**, **cloud**, and **team**.

---

## 1. Micronaut

- **DI and AOP** resolved at **compile time** → less reflection at runtime, fast startup.
- Strong **GraalVM Native Image** story.
- Useful for **microservices** and **serverless** when you want a smaller *footprint* than classic Spring.

Site: [micronaut.io](https://micronaut.io/)

---

## 2. Quarkus

- Built for **Kubernetes** and **cloud**; *extensions* for REST, JPA, Kafka, etc.
- Aggressive **build time**; strong **native image** support.
- **Red Hat** / community stack; feels like “lean Spring + cloud-first opinion”.

Site: [quarkus.io](https://quarkus.io/)

---

## 3. Vert.x

- **Event-driven**, **non-blocking**; good for **gateways**, high-concurrency I/O.
- Can run **alone** or **with Spring** (hybrids exist).
- Different mental model from classic “one thread per request” servlets.

Site: [vertx.io](https://vertx.io/)

---

## 4. Helidon (Oracle)

- **Helidon SE** (light functional API) and **Helidon MP** (MicroProfile).
- Option for shops already on Oracle / Java EE / Jakarta.

Site: [helidon.io](https://helidon.io/)

---

## 5. Minimal Java web frameworks

- **Javalin**, **Spark Java** — simple REST, less magic, fewer *batteries included* than Spring.
- Useful for **small APIs** or when you want to own the stack.

---

## 6. Kotlin on the JVM

- **Ktor** (JetBrains), **http4k** — popular in **Kotlin** projects; interoperate with Java.

---

## 7. How to choose (heuristic)

| Priority | Tendency |
|----------|----------|
| Maximum ecosystem, hiring, Java legacy | **Spring Boot** |
| Startup/memory/native, Kubernetes cloud | **Quarkus**, **Micronaut** |
| Heavy concurrent I/O, *reactive* style | **Vert.x**, Spring **WebFlux** (another *stack* in Spring) |
| Minimum framework, fine control | **Javalin** / plain HTTP libs |

Nothing stops **different** services in one org from using different stacks — align **standards** (observability, security) at the boundary.
