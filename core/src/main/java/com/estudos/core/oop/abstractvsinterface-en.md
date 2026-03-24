**PT:** [abstractvsinterface.md](abstractvsinterface.md)

# `AbstractVsInterface`

Example in: `AbstractVsInterface.java`.

## Goal

Contrast **interface** (capability contract, no mandatory state on the interface itself) with **abstract class** (can hold **state** and **partial behavior**, plus abstract methods).

## Example structure

### `interface Deslocavel`

- Declares **`void mover()`** — implementors **must** supply the body.
- In modern Java, interfaces can have `default`/`private` methods; here it is minimal: contract only.

### `abstract class Veiculo`

- Holds **shared state:** `protected final String id` initialized in the super constructor.
- Declares **`abstract void ligar()`** — each concrete vehicle defines what “ligar” means.
- **Single inheritance:** a class extends only **one** superclass.

### `Bicicleta extends Veiculo implements Deslocavel`

- **Inherits** `id` and the obligation to implement `ligar()`.
- **Implements** `mover()` from the interface.
- `main` calls `ligar()` and `mover()` on a concrete instance.

## Nuances

- **When interface:** cross-cutting capabilities (`Comparable`, `Serializable`, `Deslocavel`) — composing roles.
- **When abstract:** factor **shared code and fields** and still force subclasses to fill gaps (`ligar`).
- **`final class Bicicleta`:** prevents further subclassing; useful when the model must not be extended.

## Example limits

Does not cover **sealed** interfaces/classes (Java 17+) nor multiple inheritance of state (Java does not have it — only multiple interfaces).
