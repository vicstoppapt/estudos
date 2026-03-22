# `final`, `static`, `interface` e encapsulamento

Texto que junta quatro peças comuns de **modelagem em Java**. Código: [`FinalStaticInterfacesAndEncap.java`](FinalStaticInterfacesAndEncap.java). Para **`static` em detalhe**, ver [`StaticMembers.java`](StaticMembers.java) e [staticmembers.md](staticmembers.md). Objetos/`private` inicial: [objectsconstructorsthis.md](objectsconstructorsthis.md). **Referência vs objeto (o que “ponteiro” significa em Java):** [pointersreferencesdeepdive.md](../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/pointersreferencesdeepdive.md) no submódulo `basics-memory`.

---

## 1. `final`

### 1.1 Classe `final`

`public final class X` → **ninguém pode** `extends X`. O JDK usa isso em `String`, `Integer`, etc.: a API **fixa** o comportamento da classe; você **consome** métodos, não especializa por herança.

### 1.2 Método `final`

Em classe **não**-`final`, um método `final` **não** pode ser sobrescrito em subclasses. Útil para travar invariantes ou desempenho (JIT pode otimizar chamadas não virtuais em alguns casos).

### 1.3 Campo `final` (instância ou `static`)

- **Instância:** deve ser atribuído **uma vez** por construtor (ou no inicializador de campo). Depois disso, a **referência** (se for tipo referência) não muda; o **objeto** apontado pode ser mutável (`final List` → a lista pode receber `add`, mas a variável não pode apontar para outra lista).
- **`static final`:** constante de **classe** (ex.: `MODULO` no exemplo). Convenção: nome em `MAIÚSCULAS_COM_UNDERSCORES`.

#### 1.3.1 `final List` e `ArrayList` que cresce

O `final` aplica-se ao **campo** (à variável que guarda a referência), não ao **interior** do objeto.

- `private final List<String> itens = new ArrayList<>();` → **não** podes fazer `itens = new ArrayList<>()` mais tarde (outra lista).
- **Podes** `itens.add("x")` quantas vezes couberem em memória: estás a mutar o **mesmo** objeto `ArrayList`.

Por baixo, o `ArrayList` pode **realocar** um array interno (`elementData`) quando enche — copia elementos para um array maior. Isso é **dentro** da implementação da classe; o objeto Java `ArrayList` exposto pela tua variável **mantém a mesma identidade** (o mesmo “endereço” de objeto na heap). Por isso **não** há contradição entre “lista cresce” e “referência `final`”.

Analogia: o `final` fixa a **etiqueta** (“sempre esta casa”); dentro da casa podes **mobilar e ampliar**. Limite prático de tamanho: memória e `int` para índices/tamanho em coleções padrão — não é o `final` que impõe o teto.

**Código:** `CarrinhoDemonstracao` em [`FinalStaticInterfacesAndEncap.java`](FinalStaticInterfacesAndEncap.java) — `identidadeDaLista()` antes e depois de muitos `add`: o `identityHashCode` da **mesma** referência `itens` permanece igual.

#### 1.3.2 Vários construtores: valores iniciais diferentes, sem mudar depois

Cada construtor deve atribuir **uma vez** cada campo `final` no caminho de execução. **Instâncias diferentes** podem ficar com **valores diferentes** (ex.: `new PedidoLogico(1L)` vs `new PedidoLogico(2L, instanteFixo)`).

Depois de `new` completar, **não** podes alterar esse campo `final` nesse objeto — não é “mudar o nome mais tarde”; é **congelar** identidade/tempo de criação **por instância** após construção.

**Código:** `PedidoLogico` (dois construtores) no mesmo `.java`.

#### 1.3.3 Exemplos concretos (lógica de aplicação, não só “nome”)

| Ideia | Porque `final` ajuda |
|--------|------------------------|
| **ID de pedido / tenant** | Identidade estável; evita reassignment acidental em fluxos longos (logs, eventos, `Map`). |
| **`Instant` de criação** | Facto de auditoria imutável após construir a entidade. |
| **Dependência injetada** (`Gateway`, `Clock`) | O mesmo serviço/relogio para toda a vida do objeto — testes com mock fixo, sem trocar referência no meio. |
| **`final List` + mutar só com `add`/`remove`** | Quem guardou a referência à lista não fica com uma lista “substituída” por outra instância vazia por engano. |

**Código:** `ProcessadorFake` com `Runnable gateway` fixo no exemplo.

#### 1.3.4 Vantagens e desvantagens

**Vantagens:** estado ou dependências **garantidos** após construção; menos bugs por reassignment; em concorrência, referência `final` bem publicada é um pilar útil do modelo de memória Java; código mais legível (“isto não muda”).

**Desvantagens:** se precisares de **trocar** o valor no ciclo de vida do objeto, não podes — novo objeto ou refatoração; vários construtores exigem disciplina (cada caminho atribui todos os `final`); muitos campos `final` podem complicar builders/testes sem padrões auxiliares.

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
| `final` (campo/local/param) | Sem reatribuição da variável; objeto apontado pode ser mutável |
| `static` | Membro da classe, não da instância |
| `interface` | Contrato de métodos; `implements`; `default`/`static` desde Java 8 |
| Encapsulamento | `private` + métodos que impõem regras |
