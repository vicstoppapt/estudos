# `ChallengeSealedSwitch`

Solução: `ChallengeSealedSwitch.java`.

## Objetivo

Hierarquia **sealed** + ramificação com **`instanceof`** (estilo Java 17; patterns em `switch` ficam para Java 21).

## Nuances

- `cor(Formato)` cobre `Quadrado` e `Circulo`; com `permits` fechado, `IllegalStateException` é rede de segurança.
- Relacionar com `SealedClassesExample` e com `switch` exaustivo em versões mais novas.
