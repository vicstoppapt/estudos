**PT:** [challengevirtualhttp.md](challengevirtualhttp.md)

# `ChallengeVirtualHttp`

Solution: `ChallengeVirtualHttp.java`. Tests mock `HttpClient` and use a synchronous executor.

## Goal

Several parallel **GET**s via **`CompletableFuture.supplyAsync`**, response body discarded, collect **status codes**; injectable executor (production: virtual threads).

## Nuances

- `join()` propagates failures as `CompletionException`.
- `Thread.currentThread().interrupt()` on `InterruptedException` when propagating.
