# `AbstractVsInterface`

**English:** [abstractvsinterface-en.md](abstractvsinterface-en.md)

Exemplo em: `AbstractVsInterface.java`.

## Objetivo

Contrastar **interface** (contrato de capacidade, sem estado obrigatório na interface em si) com **classe abstrata** (pode ter **estado** e **comportamento parcial**, além de métodos abstratos).

## Estrutura do exemplo

### `interface Deslocavel`

- Declara **`void mover()`** — quem implementa **deve** fornecer o corpo.
- Em Java moderno, interfaces podem ter `default`/`private` methods; aqui é o caso mínimo: só contrato.

### `abstract class Veiculo`

- Mantém **estado comum:** `protected final String id` inicializado no construtor da super.
- Declara **`abstract void ligar()`** — cada veículo concreto define o que é “ligar”.
- **Herança simples:** uma classe só estende **uma** superclasse.

### `Bicicleta extends Veiculo implements Deslocavel`

- **Herda** `id` e a obrigação de implementar `ligar()`.
- **Implementa** `mover()` da interface.
- `main` chama `ligar()` e `mover()` em uma instância concreta.

## Nuances

- **Quando interface:** capacidades cruzadas (`Comparable`, `Serializable`, `Deslocavel`) — composição de papéis.
- **Quando abstrata:** fatorar **código e campos** compartilhados e ainda forçar subclasses a preencher lacunas (`ligar`).
- **final class `Bicicleta`:** impede nova subclasse; útil quando o modelo não deve ser estendido.

## Limite do exemplo

Não cobre **sealed** interfaces/classes (Java 17+) nem herança múltipla de estado (Java não tem — só múltiplas interfaces).
