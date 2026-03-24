# Network layers, TCP, UDP, and DNS

**PT:** [camadasetcpudp.md](camadasetcpudp.md)

Submodule **`basics-networking`**. Local example: [`NetworkingLiteracy.java`](NetworkingLiteracy.java).

---

## 1. Why “layers”

Networks are designed in **layers** to separate concerns. Two classic models:

| Model | Use |
|--------|-----|
| **OSI** (7 layers) | Teaching, documentation |
| **TCP/IP** (4 layers) | The real Internet |

For application work what matters most: **link** (Ethernet/Wi‑Fi), **IP** (addressing and routing), **TCP or UDP** (transport), **application** (HTTP, gRPC, etc.).

---

## 2. IP, ports, and socket

- **IP address** identifies a **host** on the network (IPv4 or IPv6).
- **Port** (0–65535) identifies a **service** on the host. E.g. 443 for typical HTTPS, 80 for HTTP.
- **Socket** (concept) = **IP + port** pair + protocol; in programming it is the **handle** the API exposes to read/write bytes.

**Firewall** and **NAT** change what is reachable from the public internet vs internal network — microservice and Kubernetes architecture lean heavily on this.

---

## 3. TCP vs UDP

| | **TCP** | **UDP** |
|---|---------|---------|
| Reliability | Ordered delivery, retransmission | No guarantees (best effort) |
| Connection | Connection-oriented (handshake) | No formal connection |
| Typical use | HTTP/HTTPS, SMTP, SSH, relational DBs | DNS (many queries), VoIP, real-time games, QUIC underneath |

**gRPC** on HTTP/2 uses **TCP** (and TLS). **HTTP/3** uses **QUIC** over **UDP**.

---

## 4. DNS

**DNS** maps a **name** (`api.servico.com`) to an **IP address**. It may return several IPs (load balancing); TTL affects **cache** (IP changes propagate slowly).

In architecture: **service discovery** (Consul, Eureka, internal DNS in K8s) extends the same idea with stable names.

---

## 5. Tie-in to Java

- **`java.net`**: low-level sockets, `InetAddress`, `URI`/`URL` (prefer `URI` for safe parsing).
- **HTTP client**: **`java11`** module in this repo → `HttpClient`.

Next in this submodule: [httprestegrpc-en.md](httprestegrpc-en.md).
