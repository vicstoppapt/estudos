# Camadas de rede, TCP, UDP e DNS

**English:** [camadasetcpudp-en.md](camadasetcpudp-en.md)

Submódulo **`basics-networking`**. Exemplo local: [`NetworkingLiteracy.java`](NetworkingLiteracy.java).

---

## 1. Por que “camadas”

Redes são desenhadas em **camadas** para separar responsabilidades. Dois modelos clássicos:

| Modelo | Uso |
|--------|-----|
| **OSI** (7 camadas) | Ensino, documentação |
| **TCP/IP** (4 camadas) | Internet real |

Na prática de aplicação importa sobretudo: **ligação** (Ethernet/Wi‑Fi), **IP** (endereços e encaminhamento), **TCP ou UDP** (transporte), **aplicação** (HTTP, gRPC, etc.).

---

## 2. IP, portas e socket

- **Endereço IP** identifica um **hóst** na rede (IPv4 ou IPv6).
- **Porta** (0–65535) identifica um **serviço** dentro do hóst. Ex.: 443 para HTTPS típico, 80 para HTTP.
- **Socket** (conceito) = par **IP + porta** + protocolo; em programação é o **handle** que a API expõe para ler/escrever bytes.

**Firewall** e **NAT** alteram o que é alcançável da internet pública vs rede interna — arquitetura de microserviços e Kubernetes passa muito por isto.

---

## 3. TCP vs UDP

| | **TCP** | **UDP** |
|---|---------|---------|
| Confiabilidade | Entrega ordenada, retransmissão | Sem garantias (best effort) |
| Conexão | Orientado a conexão (handshake) | Sem conexão formal |
| Uso típico | HTTP/HTTPS, SMTP, SSH, bases relacionais | DNS (muitas queries), VoIP, jogos em tempo real, QUIC por baixo |

**gRPC** em HTTP/2 usa **TCP** (e TLS). **HTTP/3** usa **QUIC** sobre **UDP**.

---

## 4. DNS

**DNS** traduz **nome** (`api.servico.com`) em **endereço IP**. Pode devolver vários IPs (balanceamento), TTL afeta **cache** (mudanças de IP demoram a propagar).

Em arquitetura: **Service discovery** (Consul, Eureka, DNS interno em K8s) prolonga a mesma ideia com nomes estáveis.

---

## 5. Ligação ao Java

- **`java.net`**: sockets de baixo nível, `InetAddress`, `URI`/`URL` (preferir `URI` para parsing seguro).
- **HTTP cliente**: módulo **`java11`** deste repositório → `HttpClient`.

Seguinte neste submódulo: [httprestegrpc.md](httprestegrpc.md).
