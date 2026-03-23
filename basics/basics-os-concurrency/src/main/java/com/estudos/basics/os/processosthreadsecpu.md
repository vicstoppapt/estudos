# Processos, threads de SO e CPU (visão de arquiteto)

**Antes / detalhe:** vocabulário, cálculos de pools e encadeamento hardware→SO→JVM — [processadorcoresethreads.md](processadorcoresethreads.md) · exemplos: [`ProcessorThreadExamples.java`](ProcessorThreadExamples.java).

Texto do submódulo **`basics-os-concurrency`**. Código de apoio: [`OsRuntimeSnapshot.java`](OsRuntimeSnapshot.java). Memória e cache: `basics-memory` → [cpucachejvmenavegador.md](../../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/cpucachejvmenavegador.md). Threads em Java (race, `synchronized`): `core` → `MultithreadingIntro`.

---

## 1. Processo (SO)

Um **processo** é uma instância de um programa em execução **isolada** pelo sistema operativo: tem o seu **espaço de endereçamento virtual** (memória própria), descritores de ficheiros, utilizador/permissões, etc. Dois processos **não** partilham memória de aplicação por defeito (comunicam por pipes, sockets, ficheiros, IPC).

- **Criar processo** é **pesado** (relativamente): o SO aloca estruturas, tabelas de páginas, carrega imagem, etc.
- **Terminar** liberta esses recursos.

Quando corres `java -jar app.jar`, o SO cria **um processo** cuja imagem executável é a JVM (e a tua aplicação corre **dentro** desse processo).

---

## 2. Thread de sistema operativo

Uma **thread** (de SO) é uma **sequência de execução** dentro de um **processo**. Threads do **mesmo** processo partilham o **mesmo espaço de endereçamento** (heap do processo, variáveis globais do runtime nativo, etc.) mas cada uma tem **stack** e **registos** próprios.

- Criar thread de SO é **mais barato** que criar processo, mas não é “grátis”: ainda há estruturas no kernel, preempção, etc.
- **Múltiplas threads** podem correr **em paralelo** em **múltiplos núcleos**; num único núcleo, o SO **multiplexa** no tempo (cada uma avança um pouco).

---

## 3. Núcleos (cores) e processador lógico

Um **CPU package** pode ter vários **núcleos físicos**. **Hyper-threading** (Intel) ou **SMT** (AMD) expõe **dois processadores lógicos** por núcleo físico em alguns chips — o SO vê “mais CPUs” para agendar.

- `Runtime.getRuntime().availableProcessors()` em Java devolve o que a JVM considera **núcleos lógicos** para **agendamento** (pode ser afetado por **cgroups** em contentores).
- **Arquitetura:** não confundir “10k pedidos HTTP/s” com “10k threads de SO” — cada thread consome memória de stack e pressão no scheduler.

---

## 4. Agendamento e troca de contexto

O **scheduler** do SO decide **qual thread** corre em **qual núcleo** e durante quanto tempo. **Preempção** interrompe uma thread para dar tempo a outra.

**Troca de contexto (context switch):** guardar estado (registos, ponteiro de stack, etc.) de uma thread e carregar outra. Custos:

- Perda de **localidade** em caches L1/L2/L3 (outro thread traz outros dados).
- Tempo de CPU “gasto” só a mudar de tarefa.

Por isso **muitíssimas** threads de SO competindo pode **degradar** desempenho — daí **pools** fixos, **virtual threads** (JDK 21+) para I/O, ou **async** com poucas threads.

---

## 5. Trabalho CPU-bound vs I/O-bound

| Tipo | O que faz | Implicação |
|------|-----------|------------|
| **CPU-bound** | Calcula o tempo todo | Escala com **núcleos**; mais threads que núcleos não aceleram o cálculo puro |
| **I/O-bound** | Espera disco, rede, BD | Threads podem **bloquear** à espera de resposta; virtual threads / async reduzem desperdício de threads de SO |

---

## 6. Contentores e limites

Em **Kubernetes** / Docker, o processo vê às vezes “toda” a máquina em `availableProcessors`, mas o ** cgroup** pode **limitar** CPUs efetivas — dimensionamento e testes de carga devem alinhar com **requests/limits**, não só com o número de cores do nó.

---

## 7. Onde aprofundar

| Destino | Tema |
|---------|------|
| [jvmeagendamento.md](jvmeagendamento.md) | JVM, thread Java, virtual threads (ponte) |
| `basics-memory` | Stack por thread, heap partilhado |
| `core` → `multithreadingintro.md` | `synchronized`, `AtomicInteger`, visibilidade |
| `java21` | Virtual threads em código |
