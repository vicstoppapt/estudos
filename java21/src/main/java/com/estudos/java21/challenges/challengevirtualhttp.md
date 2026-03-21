# `ChallengeVirtualHttp`

Solução: `ChallengeVirtualHttp.java`. Testes usam mock de `HttpClient` e executor síncrono.

## Objetivo

Vários **GET** em paralelo via **`CompletableFuture.supplyAsync`**, corpo descartado, coleta de **status codes**; executor injetável (produção: virtual threads).

## Nuances

- `join()` propaga falhas como `CompletionException`.
- `Thread.currentThread().interrupt()` em `InterruptedException` ao propagar.
