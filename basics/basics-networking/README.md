# `basics-networking` — protocolos e camadas

Submódulo do agregador `basics`: **mapa mental** para arquitetos (TCP/UDP, HTTP, gRPC, outros padrões). Não substitui RFCs nem o módulo **`java11`** (`HttpClient` em código).

## Documentos

| Ficheiro | Tema |
|----------|------|
| [camadasetcpudp.md](src/main/java/com/estudos/basics/network/camadasetcpudp.md) | Modelo em camadas, IP, portas, TCP vs UDP, DNS |
| [httprestegrpc.md](src/main/java/com/estudos/basics/network/httprestegrpc.md) | HTTP/HTTPS, REST, HTTP/2, gRPC |
| [outrosprotocolos.md](src/main/java/com/estudos/basics/network/outrosprotocolos.md) | WebSocket, filas, MQTT, TLS em uma frase |

## Código

| Classe | `exec:mainClass` |
|--------|------------------|
| `NetworkingLiteracy` | `com.estudos.basics.network.NetworkingLiteracy` |

## Maven

```bash
cd basics
mvn -q exec:java -pl basics-networking "-Dexec.mainClass=com.estudos.basics.network.NetworkingLiteracy"
```

## Relacionado

- `java11` → `HttpClientExample` (HTTP em Java).
- `java8` / `java21` — outras peças de ecosistema conforme versão.
