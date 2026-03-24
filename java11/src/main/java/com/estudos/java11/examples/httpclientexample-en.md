[PT](httpclientexample.md)

# `HttpClientExample`

Example: `HttpClientExample.java`.

## Goal

**`java.net.http` API**: **synchronous** HTTP client (`send`) with `HttpRequest`/`HttpResponse` (Java 11+).

## Nuances

- **`send` blocks** the thread until the response (body as `String` with `BodyHandlers.ofString`).
- **Async** would be `sendAsync` + `CompletableFuture` (see challenges in other modules).
- **Real network:** firewall, certificates, HTTP/2 — the example only does a simple GET.
- Closing resources: `HttpClient` is reusable; `BodySubscriber` may need closing streams in custom handlers.
