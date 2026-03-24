**PT:** [switchwhenguard.md](switchwhenguard.md)

# `SwitchWhenGuard`

Example: `SwitchWhenGuard.java`.

## Goal

**`when` guards** on `switch` with patterns: an extra boolean condition after the pattern.

## Nuances

- **Order matters:** the first matching case wins (including `when`).
- `Pedido` with invalid quantity vs high total vs normal case.
