# `final`, `static`, `interface` e encapsulamento

Texto que junta quatro peças comuns de **modelagem em Java**. Código: [`FinalStaticInterfacesAndEncap.java`](FinalStaticInterfacesAndEncap.java). Para **`static` em detalhe**, ver [`StaticMembers.java`](StaticMembers.java) e [staticmembers.md](staticmembers.md). Objetos/`private` inicial: [objectsconstructorsthis.md](objectsconstructorsthis.md).

---

## 1. `final`

### 1.1 Classe `final`

`public final class X` → **ninguém pode** `extends X`. O JDK usa isso em `String`, `Integer`, etc.: a API **fixa** o comportamento da classe; você **consome** métodos, não especializa por herança.

### 1.2 Método `final`

Em classe **não**-`final`, um método `final` **não** pode ser sobrescrito em subclasses. Útil para travar invariantes ou desempenho (JIT pode otimizar chamadas não virtuais em alguns casos).

### 1.3 Campo `final` (instância ou `static`)

- **Instância:** deve ser atribuído **uma vez** por construtor (ou no inicializador de campo). Depois disso, a **referência** (se for tipo referência) não muda; o **objeto** apontado pode ser mutável (`final List` → a lista pode receber `add`, mas a variável não pode apontar para outra lista).
- **`static final`:** constante de **classe** (ex.: `MODULO` no exemplo). Convenção: nome em `MAIÚSCULAS_COM_UNDERSCORES`.

### 1.4 Variável local e parâmetro `final`

- **Local:** não pode ser reatribuída depois da primeira atribuição. Útil para legibilidade e para usar em lambdas/anónimos internos (em versões antigas era obrigatório em alguns casos).
- **Parâmetro:** dentro do método, não podes fazer `param = outroValor`. Nada impede **mutar** o objeto referenciado se for mutável.

---

## 2. `static` (recapitulação)

- Pertence à **classe**, não a uma instância concreta.
- Campo `static` → **um** armazenamento partilhado por todas as instâncias (se existirem).
- Método `static` → não tem `this`; só acede diretamente a outros membros `static` da mesma classe (para instância, precisas de uma referência explícita).

Detalhes e contador de exemplo: [staticmembers.md](staticmembers.md).

---

## 3. `interface`

- **Contrato:** lista de comportamentos (métodos) que um tipo **compromete** implementar.
- Classe usa **`implements NomeInterface`**; pode implementar **várias** interfaces.
- A partir do Java 8: método **`default`** na interface — implementação base; a classe pode **sobrescrever**. Método **`static`** na interface — chamado como `NomeInterface.metodo()`, não herdam à classe implementadora como instância.
- **Contraste rápido com `abstract class`:** interface é sobretudo **capacidade**; classe abstrata pode carregar **estado** e construtores. Ambos permitem polimorfismo por supertipo.

No exemplo, `Reprodutor` obriga `play`; `playSilencioso` é `default`.

---

## 4. Encapsulamento

- Esconder **estado interno** (`private` em campos) e expor só **operações** (`public` métodos) que mantêm **regras** (invariantes).
- Ex.: `ContaSimples` não expõe `centavos` diretamente; só `depositar` (com validação) e leitura do saldo. Assim ninguém faz `centavos = -999` fora da classe.
- **Getter/setter** não são fim em si mesmos: só fazem sentido se **controlarem** acesso ou se fizerem parte de um contrato (beans, APIs). Ver também [objectsconstructorsthis.md](objectsconstructorsthis.md).

---

## 5. API: criador vs consumidor (ligação)

- **Consumidor:** usas `String`, `List`, `Reprodutor` como tipo declarado e chamas métodos — não precisas de publicar nada.
- **Criador:** defines `interface Reprodutor` ou classe `public` com API estável; outros módulos dependem desse contrato.
- Podes ser **os dois** no mesmo projeto (defines interface num pacote, implementas noutro).

Mais contexto: [apiconceitogeral.md](apiconceitogeral.md).

---

## 6. Resumo

| Peça | Ideia central |
|------|----------------|
| `final` (classe) | Sem subclasses |
| `final` (campo/local/param) | Sem reatribuição da variável |
| `static` | Membro da classe, não da instância |
| `interface` | Contrato de métodos; `implements`; `default`/`static` desde Java 8 |
| Encapsulamento | `private` + métodos que impõem regras |
