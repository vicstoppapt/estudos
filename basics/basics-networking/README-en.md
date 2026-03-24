# `basics-networking` — protocols and layers

**PT:** [README.md](README.md)

**Repo `estudos`:** [DOCUMENTATION.md](../../DOCUMENTATION.md) · [GLOSSARY.md](../../GLOSSARY.md).

Submodule of the `basics` aggregator: **mental map** for architects (TCP/UDP, HTTP, gRPC, other patterns). Does not replace RFCs nor the **`java11`** module (`HttpClient` in code).

## Documents

| File | Topic |
|------|--------|
| [camadasetcpudp-en.md](src/main/java/com/estudos/basics/network/camadasetcpudp-en.md) | Layered model, IP, ports, TCP vs UDP, DNS |
| [httprestegrpc-en.md](src/main/java/com/estudos/basics/network/httprestegrpc-en.md) | HTTP/HTTPS, REST, HTTP/2, gRPC |
| [outrosprotocolos-en.md](src/main/java/com/estudos/basics/network/outrosprotocolos-en.md) | WebSocket, queues, MQTT, TLS in one sentence |

## Code

| Class | `exec:mainClass` |
|--------|------------------|
| `NetworkingLiteracy` | `com.estudos.basics.network.NetworkingLiteracy` |

## Maven

```bash
cd basics
mvn -q exec:java -pl basics-networking "-Dexec.mainClass=com.estudos.basics.network.NetworkingLiteracy"
```

## Related

- `java11` → `HttpClientExample` (HTTP in Java).
- `java8` / `java21` — other ecosystem pieces per your POM.
