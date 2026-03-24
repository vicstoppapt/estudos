**PT:** [challengesealedswitch.md](challengesealedswitch.md)

# `ChallengeSealedSwitch`

Solution: `ChallengeSealedSwitch.java`.

## Goal

**Sealed** hierarchy + branching with **`instanceof`** (Java 17 style; patterns in `switch` belong in Java 21).

## Nuances

- `cor(Formato)` covers `Quadrado` and `Circulo`; with a closed `permits` list, `IllegalStateException` is a safety net.
- Relate to `SealedClassesExample` and exhaustive `switch` in newer versions.
