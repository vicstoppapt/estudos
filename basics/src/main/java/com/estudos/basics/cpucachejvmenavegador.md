# Cache da CPU, cache do navegador, RAM e JVM

Texto transversal (sem `.java` dedicado). Cruza com [memoryphysicaldeepdive.md](memoryphysicaldeepdive.md) (hierarquia física) e [memoryandreferences.md](memoryandreferences.md) (modelo Java).

---

## 1. Duas caches com o mesmo nome — ideia parecida?

**Só no conceito geral:** guardar uma **cópia** de dados que já vieram de um lugar **mais lento**, para na próxima vez **não repetir** esse caminho.

| | **Cache da CPU (L1 / L2 / L3)** | **Cache do navegador (browser)** |
|---|--------------------------------|-------------------------|
| **O quê** | Cópias de **linhas de memória** vindas da RAM (blocos de bytes: código máquina + dados) | Cópias de **recursos HTTP** (HTML, CSS, JS, imagens, …) |
| **Onde** | **Dentro do chip** (SRAM muito rápida) | **Disco** (e às vezes RAM), no seu PC, gerido pelo **browser** |
| **Quem gere** | **Hardware** da CPU — transparente para o programador | **Software** (navegador + cabeçalhos como `Cache-Control`) |
| **Para quê** | Evitar ir à **RAM** tantas vezes (latência em **nanos**) | Evitar ir à **rede** de novo (latência em **ms** ou pior) |
| **Persiste ao desligar** | **Não** (volátil) | Muitas vezes **sim** (arquivos em disco até expirarem ou você limpar) |

### Etimologia

**Cache** vem do francês *cacher* (esconder). Em informática, é um armazenamento **auxiliar** “por baixo” do caminho principal — rápido de consultar.

Em português técnico usa-se quase sempre a palavra **cache** emprestada; também se diz **memória cache** para a da CPU.

---

## 2. “Memória disponível do processador” — não confundir três coisas

Quando alguém fala nisso, costuma misturar:

1. **Cache da CPU** — pequeno, **fixo por modelo** de processador, gerido só pelo hardware.  
2. **RAM** — o que você instala na placa-mãe (GB).  
3. **Heap da JVM** (ou memória do processo `java`) — o que `-Xmx` e o SO limitam.

O número **“baixo”** que você vê ao falar de cache **é normal**: L1 em **dezenas de KB** por núcleo, L2 em **centenas de KB**, L3 em **vários MB** no total. Comparado com **8–32 GB de RAM**, parece ridículo — mas o cache é **SRAM cara** colada ao núcleo; **tem** de ser pequeno para ser rápido.

---

## 3. Como “saber” quanto cache você tem

- **Tamanhos L1/L2/L3:** especificações do **modelo da CPU** (site Intel/AMD, ferramentas como CPU-Z, documentação da máquina). São valores **de hardware**, não algo que você “aumenta” como RAM.
- **Não existe** (para aplicação normal) um “cache livre em GB” como na RAM. O CPU **não expõe** “reserva-me 2 MB de L3”; o hardware **evicta** e **carrega** linhas sozinho.
- Se você quer **memória disponível para a sua app Java**, olhe para **RAM do processo** e **heap** (`-Xmx`, VisualVM, `Runtime`, gestor de tarefas) — **isso não é** “tamanho do cache da CPU”.

---

## 4. O que é guardado no cache da CPU (não é “variável Java”)

O processador **não** pensa em `int x` ou “método inteiro” como na linguagem.

Trabalha com **linhas de cache** (típico **64 bytes**): cópias de **trechos da memória** (instruções em código máquina, dados de objetos, stack, etc.) que estão na **RAM**.

- **O quê entra/sai** é decidido pela **política do hardware** (localidade temporal e espacial).  
- É **opaco**: não há API Java para “esta variável vai para o L1”.

Há **outro processamento** no sentido de circuitos dedicados (controlador de cache, coerência entre núcleos), não é “a mesma coisa” que acessar a RAM com a mesma latência.

---

## 5. Dá para forçar dados “cruciais” a ficar no cache via JVM?

(O que significa **“não há API”** aqui: ver [apiconceitogeral.md](apiconceitogeral.md).)

**Na prática, não.**

- A JVM **não oferece** “fixar no L1/L2” nem flags `-XX:` normais para isso.  
- **Não** controlas diretamente o que o hardware mete na cache.

O que **influencia indiretamente** *cache hits* (e desempenho):

- **Localidade:** aceder a dados **contíguos** em sequência (ex.: arrays) costuma aproveitar melhor as linhas do que saltos aleatórios na memória.  
- Código **quente** em loops apertados tende a ter instruções reutilizadas no cache de **instruções**.

Isto é **micro-otimização**; em Java ainda há **GC**, **layout** mutável, **JIT** — o controle é muito mais indireto que em C em sistemas embutidos. Cenários com **cache locking** ou regras duras de tempo real são **fora** do uso típico de aplicação Java.

---

## 6. Quem “pensa” nisto: placa-mãe ou processador? Quem “programa” o que fica na cache?

### 6.1 Onde a decisão é feita (hardware)

- **Fabricante do processador** (Intel, AMD, Apple com M-series, ARM licensees, etc.) **projeta o chip**: tamanhos de L1/L2/L3, **controladores de cache**, **coerência** entre núcleos (família de protocolos tipo MESI), **prefetch** especulativo, algoritmos de **substituição** de linha (implementações reais costumam ser aproximações de LRU ou árvores PLRU — **microarquitetura**, muitas vezes **não documentada** ao nível de detalhe público).
- A **placa-mãe** fornece **soquete**, **alimentação**, **trilhas até a RAM** (DDR), slots M.2/SATA, etc. Ela **não** “decide” que endereços entram no L1: isso é **lógica dentro do pacote da CPU** (ou no die do SoC).

### 6.2 Na prática, quem “programa” o que deve ou não ser armazenado?

**Ninguém escreve um programa da aplicação que diga “mete esta variável no L1”.**

O que existe é **comportamento fixo em hardware** + **reação a acessos**:

1. O seu código (compilado para instruções) **lê/escreve endereços de memória**.
2. A **unidade de gerenciamento de memória / hierarquia de cache** da CPU, em **hardware**, em cada acesso:
   - acerta (*hit*) se a linha já está na cache;
   - falha (*miss*) e **busca** da RAM (e níveis superiores) a **linha** correspondente;
   - quando a cache enche, **expulsa** (*evict*) alguma linha segundo a **política do silício** (não é o SO que escolhe byte a byte).

Ou seja: **existe lógica**, mas é **microarquitetura** — circuitos e algoritmos **dentro do CPU**, não um arquivo de configuração que o programador Java edita.

### 6.3 Papel do SO, firmware e “programação”

- **Firmware (BIOS/UEFI)** e **SO** podem influenciar coisas **indiretas** (energia, *boost*, afinidade de CPU, em cenários muito especiais registos da CPU — MSRs — em **kernels** ou **hypervisors**). Isto **não** é o dia a dia de “decidir conteúdo da L1 por variável”.
- **Compilador / programador** influenciam **padrões de acesso** (localidade), e o **hardware reage** — mas **não** há API estável tipo `cache.put(x)`.

### 6.4 Resposta curta para entrevista

- **Quem desenha a cache?** → **Fabricante da CPU** (e o desenho do SoC no caso de chips integrados).  
- **A placa-mãe?** → Liga CPU à RAM e periféricos; **não** substitui a lógica de cache interna do processador.  
- **Quem programa o conteúdo?** → **Ninguém em software de aplicação**; é **automático no hardware** com base em **acessos à memória**.

---

## 7. Onde ver “quanto tenho disponível” para a aplicação

| Necessidade | Onde olhar |
|-------------|------------|
| RAM do SO / do processo | Gestor de tarefas (Windows), `htop` (Linux), etc. |
| Heap Java | `-Xmx`, ferramentas JVM, `Runtime.getRuntime().freeMemory()` (aproximação grosseira) |
| Profiling fino | JFR, VisualVM, async-profiler (inclui eventos de cache *miss* em alguns modos — avançado) |

O **cache da CPU** continua a ser **acelerador** por baixo; **não substitui** a noção de “memória disponível” da aplicação.

---

## 8. Resumo

1. **Cache do navegador** e **cache da CPU** compartilham a **ideia** de cópia rápida; **camada**, **tamanho** e **gestão** são totalmente diferentes.  
2. **Cache da CPU** é **pequeno por desenho**; o que lá vai são **blocos de memória**, não “métodos” como conceito de linguagem.  
3. **Não** dá para forçar dados para L1 via código Java/JVM normal; melhore **padrões de acesso** se precisar de performance extrema (e meça com profiler).  
4. Para “quanto memória tenho para a app”, pense **RAM + heap JVM**, não “GB de cache”.  
5. **Quem define a cache** é o **fabricante do processador**; o **conteúdo** é gerido por **hardware** conforme **acessos** — não por um “programa” da aplicação.

---

## 9. Leitura relacionada neste repositório

- [memoryphysicaldeepdive.md](memoryphysicaldeepdive.md) — física, swap, latências.  
- [memoryandreferences.md](memoryandreferences.md) — stack, heap, referências.  
- `core` → `jvmmemorymodelintro.md`, `GarbageCollectorBasics.java`.
