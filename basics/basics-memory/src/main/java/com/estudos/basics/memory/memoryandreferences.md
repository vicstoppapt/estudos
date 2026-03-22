# Memória, RAM e referências (visão para entrevista e revisão de arquitetura)

Texto **transversal** ao módulo `basics`: não tem `.java` dedicado; cruza com `variablesandtypes.md`, `core/jvm` e `core/imperative/PassByValue`.

Objetivo: lembrar (ou aprender de novo) **onde os dados vivem**, **o que é RAM vs disco**, **o que significa “referência”**, **o que a JVM faz “por trás”** em relação a “passagem por referência”, e **como outras linguagens** costumam diferir — linguagem típica de entrevista e de disciplinas de sistemas / arquitetura de computadores.

---

## 1. “Memória” na prática: não é tudo a mesma coisa

Quando você programa, quase sempre usa a **hierarquia de memória** do computador:

| Nível | O quê é | Velocidade | Volátil? |
|-------|---------|------------|----------|
| **Registos da CPU** | Pequenas células dentro do processador | Extremamente rápido | Sim |
| **Cache (L1, L2, L3)** | Memória muito rápida entre CPU e RAM | Muito rápido | Sim |
| **RAM** | Memória principal onde o SO e os processos carregam código e dados em execução | Rápido (vs disco) | **Sim — volátil** |
| **Disco (SSD/HDD)** | Armazenamento persistente: arquivos, instalação do JDK, etc. | Lento comparado com RAM | **Não** (persiste sem energia) |

**Volátil** = quando você desliga a máquina, o conteúdo típico da **RAM** se perde. O programa em execução, variáveis, objetos na JVM — isso está (conceitualmente) ligado à **RAM** e à **memória virtual** que o SO mapeia para RAM (e às vezes **swap** no disco quando falta RAM). **Física do PC, frequências, swap em detalhe, limites, loops, ponteiros:** [memoryphysicaldeepdive.md](memoryphysicaldeepdive.md).

### 1.1 ROM — não confundir com “disco”

**ROM** (Read-Only Memory), no PC moderno, é sobretudo **firmware** (ex.: UEFI/BIOS): fixo de fábrica ou atualizado raramente, **não** é onde a aplicação Java escreve dados do dia a dia.

Em conversa informal, às vezes dizem “ROM” quando querem dizer **armazenamento permanente**. Para programação de aplicações:

- **Impacto na RAM** o tempo todo (heap, stack, código carregado do processo).
- **Impacto no disco** quando há I/O (arquivos, banco em disco, logs), quando o SO usa **swap**, ou quando você instala/atualiza software.
- **ROM** no sentido de firmware: a aplicação normal **não reescreve** isso — outro domínio (embedded, atualização de BIOS, etc.).

Resposta curta para entrevista: *“O programa em execução trabalha principalmente em **RAM** (e caches). **Persistência** é disco. **ROM** no PC é outro assunto (firmware).”*

---

## 2. Processo, JVM e o teu programa

Quando você roda `java ...`, o SO cria um **processo**. A **JVM** é esse processo (simplificando): o SO dá a esse processo **espaço de endereçamento virtual** — páginas que podem estar em **RAM** física ou temporariamente em **swap**.

Dentro desse espaço, a JVM gere:

- **Metaspace** (desde Java 8+, metadados de classes — não é o “PermGen” antigo no heap clássico),
- **Heap** — onde ficam **a maioria dos objetos** (instâncias, arrays, `String`, etc.),
- **Stack** — **por thread**: cada chamada de método tem um **frame** com variáveis locais primitivas e **referências** (ponteiros) para objetos no heap,
- **PC register, native stack** para JNI, etc.

Isto alinha com o que está em `core` → `JvmMemoryModelIntro` / `jvmmemorymodelintro.md` (texto curto no projeto).

---

## 3. O que é uma “referência” (conceito geral)

Uma **referência** é um **valor** que **identifica** onde está um objeto na memória gerida pela JVM — em termos de entrevista, podes dizer **“como um ponteiro, mas não aritmético”**: em Java **não** podes fazer `ref + 1` para saltar na memória como em C.

- Variável de tipo `String s` → `s` guarda uma referência; o **conteúdo** da string está no **objeto** no **heap**.
- Duas variáveis podem ter **a mesma referência** (apontam para o **mesmo** objeto) ou referências **diferentes** para objetos **iguais** em conteúdo (`equals`).

---

## 4. Java: “passa por referência” ou não?

### 4.1 O que a especificação diz

Java passa **sempre argumentos por valor** (*pass-by value*):

- Se o argumento é **`int`**, o valor copiado é o **número**.
- Se o argumento é **`String s`**, o valor copiado é o **valor da referência** (uma espécie de endereço lógico) — **não** o objeto inteiro.

Por isso, na entrevista:

- **Correto:** *“Java é **pass-by value**. Para tipos referência, o valor passado é a **cópia da referência**.”*
- **Impreciso:** *“Objetos são passados por referência.”* — confunde quem pensa que a variável do chamador pode ser **reatribuída** no callee e refletir no chamador (não pode, como em `PassByValue`).

### 4.2 O que a JVM faz “por trás”

A JVM implementa isso com **ponteiros reais** (ou **compressed oops**, referências comprimidas, etc.) na implementação HotSpot — **detalhe de implementação**. O **modelo da linguagem** que importa para o programador é:

- Copias-se o **bits da referência** ou o **bits do primitivo**.
- O objeto no heap **não é duplicado** só por passar a referência como argumento — mas a **variável parâmetro** é uma **nova variável local** com uma **cópia** desse ponteiro.

**Analogia:** você dá a alguém o **endereço** da sua casa num papel. A pessoa tem uma **cópia** do endereço; se **riscar o papel** e escrever outro endereço, **a sua** folha original **não muda**. Se entrarem na casa e mudarem o sofá, **você vê a mudança** porque é a **mesma** casa — isso é **mutação do objeto** compartilhado.

---

## 5. Stack vs heap (modelo mental para entrevista)

| | **Stack (por thread)** | **Heap** |
|---|------------------------|----------|
| **O quê** | Frames de métodos; locais primitivos; variáveis que guardam **referências** | Objetos e arrays (na prática) |
| **Quem limpa** | Quando o método retorna, o frame some | **Garbage collector** remove o que já não é alcançável |
| **Compartilhamento entre threads** | Cada thread tem sua stack | **Heap compartilhado** — por isso concorrência precisa de regras (`synchronized`, etc.) |

**Nota:** alguns objetos podem ser **alocados na stack** por otimização (**escape analysis**) — otimização da JVM, **não** muda o modelo mental habitual em entrevista.

---

## 6. Outras linguagens — comparações úteis

### 6.1 C / C++

- **Ponteiros** explícitos: podes fazer aritmética, `*p`, etc.
- **C++** tem **referências** (`int& x`) que são **aliases** — semântica **diferente** de Java: podes ter pass-by-reference real de variável em C++.

### 6.2 Python (modelo mental)

Nomes são **ligações** a objetos; objetos mutáveis podem ser compartilhados. Parece “referência” como Java; a semântica de passagem é **semelhante** à ideia de passar “o valor da referência” (com nuances de implementação CPython).

### 6.3 C#

- **`ref` / `out`** permitem **pass-by-reference** explícito de variável — Java **não** tem equivalente direto para parâmetros.

### 6.4 Rust

- **Ownership** e **borrowing** (`&T`, `&mut T`) — modelo **muito** diferente; sem GC na heap da mesma forma que Java.

Para entrevista **só em Java**: domine **pass-by value + cópia da referência** e **stack/heap**; comparações curtas com C++ ou C# impressionam se você for preciso.

---

## 7. Exemplo completo (mental ou no IDE) — Java

```java
void exemplo(String a, int b) {
    a = "outro";   // só muda a cópia local da referência; quem chamou não vê mudança no SEU ponteiro
    b = 99;        // só muda a cópia local do int
}

void mutar(StringBuilder sb) {
    sb.append("!"); // muta o objeto no heap — quem chamou vê, porque ainda aponta ao MESMO objeto
}
```

Isto está implementado e comentado em `core` → `PassByValue.java`.

---

## 8. Frases prontas para entrevista

1. *“Primitivos moram como valor no frame; objetos moram no **heap** e a variável guarda **referência**.”*
2. *“Java passa **sempre por valor**; para objetos, o valor é a **referência**.”*
3. *“**RAM** volátil para execução; **disco** para persistência; **ROM** no PC não é o alvo típico da aplicação.”*
4. *“**Heap** é compartilhado entre threads; **stack** é por thread.”*

---

## 9. Onde aprofundar neste repositório

| Arquivo / módulo | Tema |
|------------------|------|
| `basics-language` → [variablesandtypes.md](../../../../../../../../basics-language/src/main/java/com/estudos/basics/variablesandtypes.md) | Primitivo vs `String` / `Integer`, wrappers |
| `core` → `passbyvalue.md` | Três cenários com `StringBuilder` e `int` |
| `core` → `jvmmemorymodelintro.md` | Lembrete textual stack/heap |
| `core` → `multithreadingintro.md` | Por que heap compartilhado exige sincronização |
| `memoryphysicaldeepdive.md` | Onde fica RAM/CPU/disco no gabinete, swap, thrashing, limites, loop infinito, ponteiros C vs Java |
| `cpucachejvmenavegador.md` | Cache CPU vs navegador; RAM/heap vs cache; o que entra na L1/L2/L3; não forçar via JVM |
| [pointersreferencesdeepdive.md](pointersreferencesdeepdive.md) | Referência vs identidade, partilha, parâmetros, `null`, C vs Java; [`ReferencesAndPointers.java`](ReferencesAndPointers.java) |
| `basics-os-concurrency` → [processosthreadsecpu.md](../../../../../../../../basics-os-concurrency/src/main/java/com/estudos/basics/os/processosthreadsecpu.md) | Processo SO, threads, núcleos, context switch |
| `basics-networking` → [camadasetcpudp.md](../../../../../../../../basics-networking/src/main/java/com/estudos/basics/network/camadasetcpudp.md) | TCP/UDP, DNS, portas; depois HTTP/gRPC nos `.md` do mesmo pacote |
| `basics-security` → [autenticacaoautorizacao.md](../../../../../../../../basics-security/src/main/java/com/estudos/basics/security/autenticacaoautorizacao.md) | AuthN/Z, JWT/OAuth em alto nível, OWASP |
| `basics-security` → [injecaosqleddos.md](../../../../../../../../basics-security/src/main/java/com/estudos/basics/security/injecaosqleddos.md) | SQLi, DDoS, defesa em camadas; [engenhariasocialgolpes.md](../../../../../../../../basics-security/src/main/java/com/estudos/basics/security/engenhariasocialgolpes.md) |
| `basics-storage` → [modelosdedados.md](../../../../../../../../basics-storage/src/main/java/com/estudos/basics/storage/modelosdedados.md) | SQL vs NoSQL, ACID/CAP, JDBC, object storage |

---

## 10. RAM “não dura” — e performance?

Quando o programa termina, o SO **recupera** a memória do processo. Durante a execução, **pressão na RAM** pode levar a **GC** mais frequente (Java) ou **swap** (lento). **Profiling** (JFR, VisualVM) liga-se a isto em trabalho real — fora do escopo mínimo deste texto, mas saber que **existe** já ajuda em entrevista sênior.
