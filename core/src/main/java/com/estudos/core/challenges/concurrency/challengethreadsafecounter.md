# `ChallengeThreadSafeCounter`

Solução em: `ChallengeThreadSafeCounter.java`. Testes verificam 100_000 após duas threads.

## Objetivo

Contador compartilhado com **`AtomicInteger`**: muitos incrementos concorrentes **sem** `synchronized` explícito na API pública.

## Por que funciona

- `incrementAndGet()` combina leitura-incremento-escrita em operação **atômica** no hardware (CAS típico).
- Evita *lost update* que ocorreria com `int` e `++` sem lock.

## Nuances

- **Escopo:** ótimo para métricas simples; estruturas compostas (ex.: mapa) exigem outras primitivas.
- Alternativa do projeto principal: `MultithreadingIntro` com `synchronized` + `int`.

## Carga do exemplo

Duas threads × **50_000** incrementos → **100_000** esperado.
