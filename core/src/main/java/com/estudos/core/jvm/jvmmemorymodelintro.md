# `JvmMemoryModelIntro`

**English:** [jvmmemorymodelintro-en.md](jvmmemorymodelintro-en.md)

Exemplo em: `JvmMemoryModelIntro.java`.

## Objetivo

Âncora de **vocabulário**: onde vivem dados na JVM em um modelo simplificado (**stack**, **heap**) e ponteiro para o tema de **concorrência** (**memory model**, *happens-before*).

## O que o programa faz

Não instrumenta a JVM nem lê MXBeans: apenas imprime um **text block** com frases-guia. O valor está em **cruzar** com teoria e depois com exemplos que mostram condição de corrida ou `volatile`.

## Stack (por thread)

- Cada **thread** tem sua **pilha de frames** (ativação de método).
- Em geral: **variáveis locais** primitivas e **referências locais** (ponteiros para objetos) ficam no frame do método — não compartilhadas entre threads.
- Quando o método retorna, o frame some; referências locais deixam de ser roots para o GC (se não houver outro caminho até o objeto).

## Heap (compartilhado)

- **Objetos** (instâncias) e **arrays** ficam no heap; **campos de instância** estão dentro do objeto.
- **Campos `static`** pertencem à classe (metaspace / representação da classe na JVM moderna — detalhe de implementação); o importante é que são **estado global** acessível por todas as threads com a mesma classe carregada.

## Metaspace / class loading (mencionado no README geral)

- Código de classes, metadados — não é o foco deste `main`, mas completa o mapa: nem tudo é “só heap”.

## JIT

- A JVM pode **compilar** bytecode para nativo após aquecimento. Comportamento observável pode mudar (otimizações), mas **semântica** da linguagem permanece; para concorrência, confiar em JLS/JMM, não em “sorte”.

## Próximo passo de estudo

- **JLS capítulo 17** — ordem, visibilidade, *happens-before*.
- Pacote `concurrency` neste projeto para ver threads e sincronização na prática.
