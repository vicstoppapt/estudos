# `MultithreadingIntro`

**English:** [multithreadingintro-en.md](multithreadingintro-en.md)

Exemplo em: `MultithreadingIntro.java`.

Se variáveis, métodos, `static`, objetos ou arrays estiverem fracos, use antes o módulo **`basics`** no repositório: [`basics/README.md`](../../../../../../../../../basics/README.md) (caminho relativo a partir deste `.md`). O resto deste texto constrói concorrência em cima disso.

---

## 1. Pré-conceitos obrigatórios

### 1.1 Processo e JVM (bem curto)

Quando você roda um programa Java, o sistema operacional trata-o como um **processo**. Dentro dele está a **JVM**, que executa o seu bytecode. A JVM gerencia **memória** (entre outras coisas o **heap**, onde ficam objetos e variáveis `static` compartilhadas pela aplicação).

### 1.2 O que é uma **thread**

Uma **thread** é um fio de execução: a sequência de instruções que a CPU vai executando **como se** fosse uma história linear. Você pode ter **várias threads** no mesmo processo; elas compartilham o mesmo espaço de memória (o mesmo heap). Por isso duas threads podem ler e escrever a **mesma variável** — e aí nascem os problemas deste documento.

### 1.3 **Single-thread** (uma só thread): o caso fácil

Imagine **só** o método `main` rodando, sem criar `new Thread(...)`.

- O processador executa **uma instrução de cada vez** (no seu código, isso parece “tudo em sequência”).
- Se você faz `int x = 0;` e depois num laço `x++` dez mil vezes, no fim `x` é **sempre** 10_000. Ninguém mais mexe em `x` enquanto o seu código roda.

**Ideia-chave:** com **uma** thread, não há “outra pessoa” alterando a mesma variável no meio do seu raciocínio. O resultado é previsível.

### 1.4 Memória compartilhada

No exemplo, `static int unsafe` e `static final AtomicInteger ATOMIC` estão no **heap** (ligados à classe, mas o importante é: **um único sítio na memória** visível por **todas** as threads).

- Thread A e thread B **veem o mesmo número** quando leem `unsafe`.
- Se ambas “somam 1” sem coordenação, o resultado final pode **não** ser o que você espera. Isso não é mágica: é **interferência** entre duas sequências de instruções.

---

## 2. Duas threads: o que muda mentalmente

Quando você faz `t1.start()` e `t2.start()`, passam a existir **duas histórias ao mesmo tempo** no mesmo programa.

- O sistema operacional / escalonador **alterna** qual thread roda em cada fatia de tempo (e em máquinas com vários núcleos, duas threads podem até rodar em paralelo em CPUs diferentes).
- Você **não** controla a ordem exata: às vezes roda um pouco da thread 1, depois da 2, depois de novo da 1… Isso se chama **intercalamento** ou **interleaving** (entrelaçamento).

**Importante:** mesmo que pareça “ao mesmo tempo”, para a lógica do seu código o que importa é: **entre duas linhas suas, outra thread pode ter executado muita coisa.**

---

## 3. O que é **race condition** (condição de corrida)

**Race condition** = o resultado do programa depende da **ordem** em que as threads são intercaladas, e essa ordem **não é garantida**. Comportamento **errático** ou **incorreto** em relação ao que queres (ex.: contador que devia ser 20_000 e sai 19_847).

### 3.1 Por que `unsafe++` é perigoso com duas threads

Em Java, `unsafe++` **não** é uma operação única indivisível no hardware. Para efeitos mentais, é **três passos**:

1. **Ler** o valor atual de `unsafe` (ex.: lês 100).
2. **Calcular** 100 + 1 = 101.
3. **Escrever** 101 de volta em `unsafe`.

Se só uma thread faz isto, está tudo bem. Se **duas** fazem ao mesmo tempo (no sentido de intercaladas), pode acontecer:

| Momento | Thread 1 | Thread 2 | Valor real de `unsafe` |
|--------|-----------|----------|------------------------|
| 1 | Lê 100 | | 100 |
| 2 | | Lê 100 | 100 |
| 3 | Escreve 101 | | 101 |
| 4 | | Escreve 101 | 101 |

As duas incrementaram “mentalmente”, mas o valor só subiu **uma vez** em relação ao ponto em que ambas leram 100. **Perdeste um incremento.** Repetido milhares de vezes, o total fica **abaixo** de 20_000.

Isso é uma **race condition** no contador: as duas threads “correm” para ler e escrever sem regras, e **atualizações perdem-se**.

### 3.2 Nome em português

“Condição de corrida” = várias threads “correndo” para o mesmo recurso; dependendo de quem lê ou escreve no meio, o resultado muda.

---

## 4. Ligação ao código: Parte 1 — `synchronized` e `LOCK`

- **`synchronized (LOCK) { unsafe++; }`** diz à JVM: **só uma thread de cada vez** pode executar este bloco **para este objeto `LOCK`**.
- Enquanto uma thread está dentro do bloco, a outra **espera**. Assim, o trio ler → somar → escrever **não** é interrompido por outra thread fazendo o mesmo no mesmo contador.

Por isso, com duas threads × 10_000 incrementos, no fim `unsafe` é **20_000**.

O objeto `LOCK` é só uma **fechadura** (na literatura Java: **monitor**). Não precisa ser “o contador”; precisa ser **o mesmo objeto** que todas as threads usam para sincronizar.

---

## 5. Parte 2 — `AtomicInteger` (outro caminho)

`AtomicInteger` oferece operações como **`incrementAndGet()`** que a JVM/hardware trata como **uma unidade** para efeitos de concorrência (em geral com **compare-and-swap**, CAS: “se o valor ainda é X, troca para X+1; senão, tenta de novo”).

- Você não precisa escrever `synchronized` em volta no seu código para esse contador simples.
- Continua existindo **uma** variável compartilhada; a diferença é que a **primitiva de incremento** já vem preparada para várias threads.

**Limitação:** isso resolve **um** inteiro com operações atômicas. Se você tem vários campos que precisam mudar **juntos** (“tudo ou nada”), normalmente precisa de **locks** ou estruturas `java.util.concurrent`.

---

## 6. `join()`

`main` chama `t1.join()` e `t2.join()` para **esperar** que essas threads terminem **antes** de imprimir o contador.

Sem `join()`, `main` poderia imprimir **enquanto** as threads ainda estavam incrementando — você veria um número **no meio**, não o final.

---

## 7. `volatile` (para não confundires)

**`volatile`** num campo ajuda com **visibilidade** (leituras/escritas não ficam “escondidas” de forma enganosa entre threads). **Não** torna `count++` atômico: continua sendo ler + somar + escrever em passos separados. Para este contador, **`volatile` sozinho não resolve**.

---

## 8. Resumo em ordem de estudo

1. **Uma thread** → sequência simples; `++` numa variável é previsível.
2. **Várias threads** + **mesma variável** → intercalamento imprevisível.
3. **`unsafe++`** → três passos → **race condition** → contagens erradas.
4. **`synchronized`** → exclusão mútua → um incremento completo de cada vez.
5. **`AtomicInteger`** → incremento preparado para concorrência.
6. **`join`** → esperar o fim antes de ler o resultado.

## 9. `meuPlayground()`

No `.java`, tente **remover** o `synchronized` de `incrementarSync` e rode várias vezes: o primeiro print deve **variar** abaixo de 20_000. Isso **é** condição de corrida na prática.
