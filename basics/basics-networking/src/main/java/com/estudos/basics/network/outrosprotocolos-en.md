# Other protocols and patterns (quick view)

**PT:** [outrosprotocolos.md](outrosprotocolos.md)

**`basics-networking`**. HTTP/gRPC: [httprestegrpc-en.md](httprestegrpc-en.md).

---

## 1. WebSocket

Long-lived **full-duplex** connection over HTTP *upgrade*. Useful for **push** to the browser (chat, live dashboards). Does not replace REST for classic CRUD.

---

## 2. Queues and messaging (AMQP, Kafka, SQS…)

- **Producer** sends **messages** to a **broker** or managed service; **consumers** process with **at-least-once** / **exactly-once** depending on product and config.
- **Temporal decoupling:** traffic spikes absorbed by the queue.
- **AMQP** (RabbitMQ), **Kafka** (partitioned log), **SQS/SNS** (cloud) — choice depends on ordering, retention, semantics, and cost.

---

## 3. MQTT

Lightweight, widely used in **IoT** and telemetry. Publish/subscribe with **topics**; low bandwidth.

---

## 4. TLS in one sentence

**TLS** (certificates, handshake) protects the **channel**. It does not replace **application authentication** (JWT, mTLS, API keys) nor **authorization** (who may do what). More detail: `basics-security` → [criptografiaehash-en.md](../../../../../../../../basics-security/src/main/java/com/estudos/basics/security/criptografiaehash-en.md).

---

## 5. GraphQL

**One** HTTP route (usually) with a client **query**; server returns **only requested fields**. **Flexible** for UIs; watch **query** complexity and **CDN** caching (harder than REST by URL).

---

## 6. Architecture checklist

- **Synchronous vs asynchronous:** direct HTTP/gRPC vs queue/events.
- **Public vs internal:** JSON/OpenAPI vs gRPC.
- **Latency and fan-out:** N+1 HTTP calls between microservices.
- **Resilience:** timeouts, idempotent retries, *circuit breaker* (patterns, not protocols).
