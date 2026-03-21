# `HttpClientExample`

Exemplo: `HttpClientExample.java`.

## Objetivo

API **`java.net.http`**: cliente HTTP **síncrono** (`send`) com `HttpRequest`/`HttpResponse` (Java 11+).

## Nuances

- **`send` bloqueia** a thread até resposta (corpo como `String` com `BodyHandlers.ofString`).
- **Assíncrono** seria `sendAsync` + `CompletableFuture` (ver desafios em outros módulos).
- **Rede real:** firewall, certificados, HTTP/2 — o exemplo só faz GET simples.
- Fechar recursos: `HttpClient` é reutilizável; `BodySubscriber` pode precisar fechar streams em handlers custom.
