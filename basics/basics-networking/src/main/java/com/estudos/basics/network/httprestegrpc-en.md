# HTTP, REST, HTTP/2, and gRPC

**PT:** [httprestegrpc.md](httprestegrpc.md)

Part of **`basics-networking`**. Lower layers: [camadasetcpudp-en.md](camadasetcpudp-en.md). HTTP in Java: **`java11`** module → [HttpClientExample.java](../../../../../../../../../java11/src/main/java/com/estudos/java11/examples/HttpClientExample.java) (path from this `.md` on disk).

---

## 1. HTTP and HTTPS

**HTTP** is a **request/response** protocol (usually): client sends **method** + **path** + **headers** (+ optional body); server responds with **status code**, headers, and body.

**HTTPS** = HTTP over **TLS** (formerly “SSL”). Provides **confidentiality** and **integrity** on the channel and (with valid certificates) **server authentication**.

Common methods:

| Method | Typical REST use |
|--------|------------------|
| `GET` | Read resource (idempotent) |
| `POST` | Create action or resource |
| `PUT` / `PATCH` | Replace / update |
| `DELETE` | Remove |

**2xx** success, **4xx** client error, **5xx** server error — architects use this in **SLAs** and **contracts** between teams.

---

## 2. REST (style, not a single RFC)

**REST** describes an **API style** over HTTP: resources with **URLs**, **HTTP methods**, **stateless** requests (state in tokens/sessions explicitly), hypermedia in some designs (HATEOAS, less common in plain JSON).

**OpenAPI** (Swagger) documents many “REST” APIs in a **machine-readable** way.

---

## 3. HTTP/1.1 vs HTTP/2

- **HTTP/1.1**: many parallel connections or limited pipelining; repeated headers.
- **HTTP/2**: **multiplexed** streams on the **same** TCP connection, compressed headers (HPACK), server push (less used).

Impact: lower **connection** overhead in browsers and microservices with many calls.

---

## 4. gRPC

**gRPC** uses **HTTP/2** as transport and **Protobuf** (usually) as a **binary** message format and contract (`*.proto`).

| Aspect | REST + JSON | gRPC + Protobuf |
|--------|-------------|-----------------|
| Format | Human-readable text | Compact binary |
| Contract | OpenAPI common | `.proto` + code generation |
| Streaming | Possible (SSE, WebSocket separately) | **Unary**, **server/client/bidi streaming** native |
| Browser | Native | Needs **gRPC-Web** or proxy |

Good for **internal** service-to-service **backend** communication; **public** third-party APIs often stay **REST/OpenAPI** for simpler consumption.

---

## 5. Where to practice in this repo

| Module | What |
|--------|------|
| `java11` | `HttpClient`, HEAD, tests with mock |
| `java21` | Modern examples if applicable to your POM |

More application protocols: [outrosprotocolos-en.md](outrosprotocolos-en.md).
