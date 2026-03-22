# JVM, processo único e threads Java

Complemento de [processosthreadsecpu.md](processosthreadsecpu.md). Foco: **o que acontece quando usas `new Thread(...).start()`** e como isto se relaciona com o `core`.

---

## 1. Uma JVM ≈ um processo SO (simplificado)

Na configuração típica, **uma** instância da tua aplicação Java é **um** processo do sistema operativo. Dentro dele:

- **Heap** e **metaspace** (metadados de classes) são **partilhados** por todas as threads Java.
- Cada **thread Java** tem **stack** próprio (frames de métodos, variáveis locais, referências).

Isto alinha com `basics-memory` → [memoryandreferences.md](../../../../../../../../basics-memory/src/main/java/com/estudos/basics/memory/memoryandreferences.md).

---

## 2. Thread Java e thread de SO (modelo clássico)

Nas implementações **tradicionais** (thread de plataforma / *platform thread*), uma thread Java **mapeia** para uma **thread do SO** (1:1 em muitos ambientes). Por isso:

- **Criar** muitas threads Java ≈ criar muitas threads de SO → pressão no scheduler e memória de stack.
- **Bloqueio** em I/O (socket read, JDBC) **bloqueia** essa thread de SO.

---

## 3. Virtual threads (JDK 21+)

**Virtual threads** são leves na JVM: quando bloqueiam em I/O, o runtime pode **desmontar** a continuação e usar **poucas** threads de SO para muitas tarefas lógicas. **Não** são magia para CPU-bound puro — para isso continuas a precisar de núcleos e algoritmos paralelos adequados.

Estudo de código: módulo **`java21`** neste repositório (`VirtualThreadsExample`, desafios).

---

## 4. O que estudar no `core`

| Ordem sugerida | Classe / doc |
|----------------|--------------|
| Modelo de memória | `JvmMemoryModelIntro` / `jvmmemorymodelintro.md` |
| Concorrência prática | `MultithreadingIntro` / `multithreadingintro.md` |
| Desafio | `ChallengeThreadSafeCounter` |

O `basics-os-concurrency` dá o **vocabulário de SO**; o `core` dá **semântica Java** (visibilidade, locks, APIs).

---

## 5. Frases úteis em revisão de arquitetura

- *“Java corre num **processo**; threads partilham **heap**; cada thread tem **stack** própria.”*
- *“Thread de plataforma ≈ thread de SO; **pool** limita o fan-out no scheduler.”*
- *“**Virtual threads** ajudam **I/O-bound**; **CPU-bound** escala com **núcleos** e desenho do algoritmo.”*
