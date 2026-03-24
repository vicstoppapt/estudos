# Processador, núcleos (cores) e threads — guia detalhado

**English:** [processadorcoresethreads-en.md](processadorcoresethreads-en.md)

**Idioma:** [English version](processadorcoresethreads-en.md) · **Glossário PT ↔ EN:** [glossary-os-concurrency.md](glossary-os-concurrency.md)

Leitura **antes** de mergulhar em JVM e código de concorrência: vocabulário exacto, o que cada camada “chama” ao resto, como **contar** recursos e como **dimensionar** uso de threads. Complementa o resumo em [processosthreadsecpu.md](processosthreadsecpu.md) e [jvmeagendamento.md](jvmeagendamento.md). Exemplos executáveis: [`ProcessorThreadExamples.java`](ProcessorThreadExamples.java), [`OsRuntimeSnapshot.java`](OsRuntimeSnapshot.java).

---

## Como executar

**Pré-requisito:** JDK 17+ (mesmo `release` que o POM pai em `basics/`).

1. **Desde a pasta do agregador `basics`** (caminho típico: `…/estudos/basics`):

```bash
mvn -q -pl basics-os-concurrency compile
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

2. **Só dentro de `basics-os-concurrency`** (sem `-pl`):

```bash
cd basics-os-concurrency
mvn -q compile
mvn -q exec:java "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
```

**PowerShell (Windows):** mantém aspas em torno de `-Dexec.mainClass=...` como acima. **IDE:** executa o `main` de `ProcessorThreadExamples` ou `OsRuntimeSnapshot` com o módulo `basics-os-concurrency` no *classpath*.

---

## 1. Vocabulário (hardware → SO → processo)

| Termo (EN) | PT usual | O que é |
|------------|----------|---------|
| **CPU** / **processor** | processador, CPU | Circuito que executa instruções máquina. Num PC moderno há tipicamente **um chip** (socket) com vários núcleos. |
| **Socket** | encaixe, socket | Conector físico da CPU na placa-mãe. Máquinas desktop: 1 socket comum; servidores: vários. |
| **Physical core** | núcleo físico | Unidade de execução **real** dentro do chip: pode executar instruções de **uma** sequência de programa de cada vez (por núcleo), salvo SMT. |
| **Logical processor** / **logical CPU** | CPU lógica, núcleo lógico | O que o **SO enumera** como “CPU 0, CPU 1, …”. Com **SMT** (Hyper-Threading Intel, SMT AMD), **um** núcleo físico expõe **duas** CPUs lógicas ao SO. |
| **SMT / Hyper-Threading** | “dois threads por núcleo” | Partilha **mais** unidades do pipeline entre duas sequências lógicas. Ganho típico **inferior a 2×** em trabalho misto; em CPU-bound homogéneo pode ser modesto. |
| **Process** | processo | Programa em execução **isolado**: espaço de endereçamento próprio, PID, tabelas do kernel. |
| **OS thread** | thread de sistema, thread do kernel | Unidade de execução **agendável** pelo SO **dentro** de um processo; partilha memória com outras threads do mesmo processo; stack e registos próprios. |

**“Core”** no dia-a-dia pode significar **físico** ou **lógico**. Em dimensionamento e em APIs como `Runtime.getRuntime().availableProcessors()`, quase sempre interessa o **lógico** (o que o scheduler pode usar).

---

## 2. Como isto encadeia (quem chama o quê)

1. **Hardware** expõe CPUs lógicas (interrupts, timers, caches por núcleo/partilhados).
2. O **kernel** mantém filas de threads **prontas** (*runnable*), aplica **política de agendamento** (quem corre em que CPU e por quanto tempo — *quantum*).
3. Cada **processo** tem uma ou mais **threads de SO**; o kernel **não** executa “linhas Java” — executa **código máquina** (nativo da JVM + JIT).
4. A **JVM** corre **dentro de um processo**; **threads Java** (plataforma) mapeiam em geral **1:1** para threads de SO; **virtual threads** (JDK 21+) desacoplam tarefas lógicas de threads de SO em I/O.

Frase útil: *o SO agenda **threads de kernel**; a JVM pede threads ao SO; o teu código agenda **tarefas** em cima desse modelo.*

---

## 3. O que é um “core” na prática

- **Núcleo físico:** conta de “motores” reais de execução no chip (ex.: 8 P-cores + 4 E-cores num laptop híbrido — o SO vê todos como CPUs lógicas enumeráveis).
- **Núcleo lógico:** o que `top`, Gestor de Tarefas ou `availableProcessors()` contam como **unidade independente** para agendar trabalho.

**Porque importa:** para **CPU-bound** puro, o teto de **ganho paralelo** está ligado a quantas instruções **realmente** avançam em paralelo nos núcleos físicos; CPUs lógicas SMT ajudam quando há bolhas no pipeline (misses, I/O, mistura de cargas).

---

## 4. O que é uma “thread” (sem confundir)

| Thread de **SO** | Thread **Java** (plataforma) | **Virtual thread** (Java 21+) |
|------------------|------------------------------|--------------------------------|
| Entidade agendada pelo kernel | Objeto `Thread` que o runtime liga a uma thread de SO (típico) | Continuação leve; bloqueio em I/O pode libertar a thread de SO |
| Stack e contexto no kernel / processo | Stack Java + estado JVM | Pilha lógica gerida pelo runtime |
| Criar muitas = pressão no scheduler | `new Thread(...).start()` caro em massa | Milhares viáveis para estilo “uma tarefa por pedido” I/O-bound |

Duas threads Java **no mesmo processo** partilham **heap**; cada uma tem **stack** própria (ver `basics-memory`).

---

## 5. Como “calcular” e dimensionar (regras de bolso)

### 5.1 Quantos processadores vê a JVM?

```java
int n = Runtime.getRuntime().availableProcessors();
```

- Devolve o número de **processadores disponíveis à JVM** para **tarefas paralelas** — na prática, **CPUs lógicas** visíveis (pode ser afetado por **cgroups** em contentores: vê 32 do host mas só pode usar 2 CPUs efetivas).

**Não** há API padrão Java multiplataforma para “só núcleos físicos”; para isso usa ferramentas de SO ou bibliotecas nativas.

### 5.2 Pools para trabalho **CPU-bound**

Objetivo: evitar **mais threads runnable** que unidades que realmente executam instruções em paralelo (com folga modesta).

- **Regra comum:** tamanho do pool ≈ **n** ou **n + 1**, com `n = availableProcessors()`.
- **Porquê “+1”?** Em alguns cenários, uma thread pode estar a fazer trabalho mínimo enquanto outra está quase a terminar — pequeno amortecimento (não é mágica).

**Mais** threads que núcleos úteis em CPU-bound → **contestação** (locks), **context switches** e **pior** throughput.

### 5.3 Pools para trabalho **I/O-bound** (bloqueante)

Enquanto uma thread **espera** rede/disco, o núcleo pode servir outra. Daí pools **maiores** que `n` — o valor depende de **latência**, **taxa de pedidos** e **memória** (cada thread de plataforma consome stack).

- Virtual threads (JDK 21+) mudam o desenho: muitas tarefas lógicas, **poucas** threads de SO.

### 5.4 Paralelismo de streams / `ForkJoinPool.commonPool()`

O *common pool* usa por defeito **até** `max(n-1, 1)` workers em muitas JVMs (detalhe de implementação — não hardcodes em regra de negócio). O importante: **paralelismo** está ligado a `availableProcessors()`, não ao número de tarefas que submetes.

### 5.5 Contentores (Docker / Kubernetes)

- `availableProcessors()` pode **não** coincidir com **CPU quota** real.
- Dimensiona **requests/limits** e testa com carga; não assumes “núcleos do nó” = “núcleos da minha pod”.

---

## 6. Custos que entram na “conta”

- **Context switch:** trocar de thread → invalida localidade de cache, gasta ciclos no kernel.
- **Contenção:** muitas threads a lutar pelo mesmo lock ou barramento de memória.
- **Memória:** cada thread de plataforma ≈ **centenas de KB a MB** de stack (configurável `-Xss`); soma rápido.

---

## 7. Ferramentas para inspeccionar a máquina (fora do Java)

| SO | Onde ver |
|----|----------|
| Windows | Gestor de Tarefas → CPU → núcleos lógicos / físicos |
| Linux | `lscpu`, `/proc/cpuinfo` |
| macOS | `sysctl -n hw.ncpu`, `sysctl hw.physicalcpu` |

Cruza com o que a JVM imprime em `ProcessorThreadExamples` / `OsRuntimeSnapshot`.

---

## 8. Onde continuar no repositório

| Ordem | Recurso |
|-------|---------|
| 1 | [processosthreadsecpu.md](processosthreadsecpu.md) — resumo arquiteto · EN: [processosthreadsecpu-en.md](processosthreadsecpu-en.md) |
| 2 | [jvmeagendamento.md](jvmeagendamento.md) — JVM e virtual threads · EN: [jvmeagendamento-en.md](jvmeagendamento-en.md) |
| 3 | [Glossário PT/EN](glossary-os-concurrency.md) |
| 4 | `basics-memory` — stack por thread, heap partilhado |
| 5 | `core` — `MultithreadingIntro`, `JvmMemoryModelIntro` |
| 6 | `java21` — virtual threads em código |

---

## 9. Referências externas (leitura opcional)

- Documentação Oracle: `Runtime.availableProcessors()`
- Schedulers Linux: CFS ( Completely Fair Scheduler ) — conceito de *fairness* e quantum
- Intel / AMD: documentação de SMT para entender limites de paralelismo real
