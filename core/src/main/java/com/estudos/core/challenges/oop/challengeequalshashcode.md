# `ChallengeEqualsHashCode`

Solução em: `ChallengeEqualsHashCode.java`. Testes cobrem `get` com chave equal.

## Objetivo

Demonstrar que **`equals` sem `hashCode` coerente** quebra **`HashMap.get`** para chaves “novas” mas semanticamente iguais.

## O que `Quebrado` faz

- `equals` baseado no campo `k`.
- `hashCode` delega a `Objects.hash(k)` — **comente** o método (ou retorne constante errada) para ver `map.get(busca)` voltar **`null`** mesmo com `equals` verdadeiro na lógica desejada, porque o mapa procura no **bucket errado**.

## Nuances

- Contrato: `equal` → **mesmo** `hashCode` (obrigatório); o inverso não precisa valer.
- **Mutabilidade:** se a chave mutar após `put`, o mapa pode “perder” a entrada.
- Relacionar com `oop.EqualsHashCodeContract` e `equalshashcodecontract.md`.

## Experimento

Como sugerido no Javadoc do fonte: comente `hashCode` e rode `mvn test` ou `main`.
