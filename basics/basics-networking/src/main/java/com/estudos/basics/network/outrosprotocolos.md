# Outros protocolos e padrões (visão rápida)

**English:** [outrosprotocolos-en.md](outrosprotocolos-en.md)

**`basics-networking`**. HTTP/gRPC: [httprestegrpc.md](httprestegrpc.md).

---

## 1. WebSocket

**Ligação longa** full-duplex sobre HTTP *upgrade*. Útil para **push** ao browser (chat, dashboards ao vivo). Não substitui REST para CRUD clássico.

---

## 2. Filas e mensagens (AMQP, Kafka, SQS…)

- **Produtor** envia **mensagens** para um **broker** ou serviço gerido; **consumidores** processam com **at-least-once** / **exactly-once** conforme produto e configuração.
- **Desacoplamento** temporal: pico de tráfego absorvido pela fila.
- **AMQP** (RabbitMQ), **Kafka** (log particionado), **SQS/SNS** (cloud) — escolha depende de ordem, retenção, semântica e custo.

---

## 3. MQTT

Leve, muito usado em **IoT** e telemetria. Publicar/subscrever com **tópicos**; baixa largura de banda.

---

## 4. TLS numa frase

**TLS** (certificados, handshake) protege o **canal**. Não substitui **autenticação de aplicação** (JWT, mTLS, API keys) nem **autorização** (quem pode fazer o quê). Mais detalhe: `basics-security` → [criptografiaehash.md](../../../../../../../../basics-security/src/main/java/com/estudos/basics/security/criptografiaehash.md).

---

## 5. GraphQL

**Uma** rota HTTP (em geral) com **query** que o cliente formula; servidor devolve **só os campos** pedidos. **Flexível** para UIs; cuidado com **complexidade** de queries e **cache** em CDN (mais difícil que REST por URL).

---

## 6. Checklist de arquitetura

- **Síncrono vs assíncrono:** HTTP/gRPC direto vs fila/eventos.
- **Público vs interno:** JSON/OpenAPI vs gRPC.
- **Latência e fan-out:** N+1 chamadas HTTP entre microserviços.
- **Resiliência:** timeouts, retries idempotentes, *circuit breaker* (padrões, não protocolos).
