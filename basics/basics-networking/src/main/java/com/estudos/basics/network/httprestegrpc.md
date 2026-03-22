# HTTP, REST, HTTP/2 e gRPC

Parte do **`basics-networking`**. Camadas inferiores: [camadasetcpudp.md](camadasetcpudp.md). HTTP em Java: módulo **`java11`** → [HttpClientExample.java](../../../../../../../../../java11/src/main/java/com/estudos/java11/examples/HttpClientExample.java) (caminho a partir deste `.md` no disco).

---

## 1. HTTP e HTTPS

**HTTP** é um protocolo **pedido/resposta** (em geral): cliente envia **método** + **caminho** + **cabeçalhos** (+ corpo opcional); servidor responde com **código de estado**, cabeçalhos e corpo.

**HTTPS** = HTTP sobre **TLS** (antes “SSL”). Garante **confidencialidade** e **integridade** no canal e (com certificados válidos) **autenticação** do servidor.

Métodos comuns:

| Método | Uso típico em REST |
|--------|---------------------|
| `GET` | Ler recurso (idempotente) |
| `POST` | Criar ação ou recurso |
| `PUT` / `PATCH` | Substituir / atualizar |
| `DELETE` | Remover |

Códigos **2xx** sucesso, **4xx** erro do cliente, **5xx** erro do servidor — arquitetos usam isto em **SLAs** e **contratos** entre equipas.

---

## 2. REST (estilo, não um único RFC)

**REST** descreve um **estilo** de API sobre HTTP: recursos com **URLs**, uso de **métodos** HTTP, **stateless** entre pedidos (estado em tokens/sessões explícitas), hipermedia em alguns desenhos (HATEOAS, menos comum em JSON puro).

**OpenAPI** (Swagger) documenta muitas APIs “REST” de forma **máquina-legível**.

---

## 3. HTTP/1.1 vs HTTP/2

- **HTTP/1.1**: muitas ligações paralelas ou *pipelining* limitado; cabeçalhos repetidos.
- **HTTP/2**: **multiplex** de streams na **mesma** ligação TCP, cabeçalhos comprimidos (HPACK), server push (menos usado).

Impacto: menor **overhead** de ligações em browsers e microserviços com muitas chamadas.

---

## 4. gRPC

**gRPC** usa **HTTP/2** como transporte e **Protobuf** (em geral) como **formato de mensagem** binário e contrato (`*.proto`).

| Aspeto | REST + JSON | gRPC + Protobuf |
|--------|-------------|-----------------|
| Formato | Texto legível | Binário compacto |
| Contrato | OpenAPI comum | `.proto` + geração de código |
| Streaming | Possível (SSE, WebSocket à parte) | **Unary**, **server/client/bidi streaming** nativos |
| Browser | Nativo | Precisa **gRPC-Web** ou proxy |

Bom para **comunicação interna** serviço-a-serviço em **backends**; **API pública** para terceiros continua muitas vezes em **REST/OpenAPI** por simplicidade de consumo.

---

## 5. Onde praticar neste repositório

| Módulo | O quê |
|--------|--------|
| `java11` | `HttpClient`, HEAD, testes com mock |
| `java21` | Exemplos modernos se aplicável ao teu POM |

Mais protocolos de aplicação: [outrosprotocolos.md](outrosprotocolos.md).
