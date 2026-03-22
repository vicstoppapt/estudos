# Outros frameworks e runtimes na JVM (além de Spring Boot)

**Agregador `frameworks`**. Spring como referência: [springbootniveis.md](springbootniveis.md). No mesmo repositório há exemplos paralelos: **`frameworks-micronaut`** (Netty, Serde, validação) e **`frameworks-quarkus`** (REST/JAX-RS, CDI) — ver [`README.md`](../README.md).

Esta lista é **orientativa** — produtos evoluem; compara **arranque**, **memória**, **ecossistema**, **cloud** e **equipa**.

---

## 1. Micronaut

- **DI e AOP** resolvidos em **tempo de compilação** (*compile-time*) → menos reflexão em runtime, arranque rápido.
- Boa integração com **GraalVM Native Image**.
- Útil para **microserviços** e **serverless** quando queres *footprint* menor que Spring tradicional.

Site: [micronaut.io](https://micronaut.io/)

---

## 2. Quarkus

- Pensado para **Kubernetes** e **cloud**; *extensions* para REST, JPA, Kafka, etc.
- **Build time** agressivo; suporte forte a **native image**.
- Stack **Red Hat** / comunidade; sensação próxima a “Spring enxuto + opinião cloud-first”.

Site: [quarkus.io](https://quarkus.io/)

---

## 3. Vert.x

- **Event-driven**, **não bloqueante**; bom para **gateways**, *high concurrency* I/O.
- Pode usar-se **sozinho** ou **com Spring** (híbridos existem).
- Modelo mental diferente de “um thread por pedido” clássico em servlet.

Site: [vertx.io](https://vertx.io/)

---

## 4. Helidon (Oracle)

- **Helidon SE** (API funcional leve) e **Helidon MP** (MicroProfile).
- Opção em empresas já no ecossistema Oracle / Java EE / Jakarta.

Site: [helidon.io](https://helidon.io/)

---

## 5. Frameworks web “minimalistas” (Java)

- **Javalin**, **Spark Java** — REST simples, menos magia, menos *batteries included* que Spring.
- Úteis para **APIs pequenas** ou quando queres controlar tu o stack.

---

## 6. Kotlin na JVM

- **Ktor** (JetBrains), **http4k** — populares em projetos **Kotlin**; interoperam com Java.

---

## 7. Como escolher (heurística)

| Prioridade | Tendência |
|------------|-----------|
| Máximo ecossistema, hiring, legado Java | **Spring Boot** |
| Arranque/memória/native, cloud Kubernetes | **Quarkus**, **Micronaut** |
| Muito I/O concorrente, estilo *reactive* | **Vert.x**, Spring **WebFlux** (outro *stack* no mundo Spring) |
| Mínimo framework, controlo fino | **Javalin** / libs HTTP puras |

Nada impede **vários** serviços na mesma organização usarem stacks diferentes — alinha **padrões** (observabilidade, segurança) na fronteira.
