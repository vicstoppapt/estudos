# Core — Java para entrevistas e funcionamento da linguagem

Objetivo: **perguntas comuns em entrevista** e **como a JVM/linguagem se comportam**. Os pacotes agrupam tema (`imperative`, `oop`, …); a **ordem de estudo** abaixo é a referência (do conteúdo **mais fácil e menos crítico** ao **mais difícil e mais importante** no dia a dia / entrevista sênior).

**Pré-requisito opcional:** se `String`, laços, `new`, `static` ou arrays estiverem em falta, percorra primeiro o módulo **`basics`** (irmão desta pasta em `estudos/basics/README.md`).

## Ordem sugerida de estudo

| # | Classe (`exec:mainClass`) | Por que esta ordem |
|---|---------------------------|-------------------|
| 1 | `com.estudos.core.imperative.StringImmutability` | Concreto; `String`, `==` vs `equals`, pool — poucos pré-requisitos. |
| 2 | `com.estudos.core.imperative.PassByValue` | Base de parâmetros (primitivo vs referência); aparece muito em entrevista. |
| 3 | `com.estudos.core.imperative.InitializationOrder` | Detalhe de linguagem; cai em prova, **menos** no trabalho cotidiano. |
| 4 | `com.estudos.core.exceptions.ExceptionsHierarchy` | Checked vs unchecked, `finally` — leitura direta. |
| 5 | `com.estudos.core.oop.ObjectMethods` | `toString`, `equals`, `hashCode` na prática antes do contrato formal. |
| 6 | `com.estudos.core.oop.AbstractVsInterface` | Modelagem OOP; herança simples + interfaces. |
| 7 | `com.estudos.core.generics_collections.GenericsBasics` | Erasure, `List<?>` — abstrato, mas necessário para APIs. |
| 8 | `com.estudos.core.generics_collections.CollectionsOverview` | `List` / `Set` / `Map` no dia a dia; complexidade em alto nível. |
| 9 | `com.estudos.core.oop.EqualsHashCodeContract` | **Muito cobrado:** `HashMap` / `HashSet` e o contrato. |
| 10 | `com.estudos.core.oop.ComparableComparator` | Ordenação; APIs `Comparable` / `Comparator`. |
| 11 | `com.estudos.core.declarative.DeclarativeVsImperative` | Exige noção de lambda/stream (relembre o módulo **java8** se travar). |
| 12 | `com.estudos.core.jvm.JvmMemoryModelIntro` | Stack, heap, class loading — modelo mental para o resto. |
| 13 | `com.estudos.core.jvm.GarbageCollectorBasics` | GC em visão entrevista; complementa o item anterior. |
| 14 | `com.estudos.core.concurrency.MultithreadingIntro` | **Mais difícil** (race, `synchronized`, `AtomicInteger`); **crítico** em backend concorrente. |

### Desafios (depois dos exemplos relacionados)

| Ordem | Classe | Depois de estudar… |
|-------|--------|-------------------|
| 1 | `challenges.collections.ChallengeAnagram` | `CollectionsOverview`, `GenericsBasics` |
| 2 | `challenges.oop.ChallengeEqualsHashCode` | `EqualsHashCodeContract` |
| 3 | `challenges.concurrency.ChallengeThreadSafeCounter` | `MultithreadingIntro` |

---

## `package-info.java` — o que é

Arquivo Java cuja única função é carregar **documentação do pacote** (o nome do pacote no topo deve bater com a pasta). Ferramentas como **JavaDoc** usam esse texto como página de “visão geral” do pacote. Não é uma classe com `main`; não precisa importar nas outras classes.

**Texto por tema:** em cada pacote há um **`README.md`** (índice / visão geral). Vários pacotes de exemplo também têm **um `.md` por classe** no mesmo diretório (ex.: `generics_collections/genericsbasics.md` ↔ `GenericsBasics.java`) com nuances e teoria mais longas; o `.java` continua com comentários de fluxo.

**Comentários no `.java`:** explicam **o que o código faz** (campos, blocos, chamadas como `join`) no estilo de programador. **Conceito e nuances** ficam no **`README.md` índice** do pacote e nos **`.md` por classe** (quando existirem); não se mistura HTML nem tutorial longo dentro do fonte.

## Seu playground em cada exemplo

Método **`meuPlayground()`** vazio + **`// meuPlayground();`** no `main`. Experimento seu; não é o desafio em `challenges.*`.

## Estrutura por pacote (tema, não ordem)

| Pacote | Paradigma / foco | Classes |
|--------|------------------|---------|
| `com.estudos.core.imperative` | Imperativo: passos, estado, tipos base | `StringImmutability`, `PassByValue`, `InitializationOrder` |
| `com.estudos.core.exceptions` | Erros e fluxo de exceções | `ExceptionsHierarchy` |
| `com.estudos.core.oop` | OOP: contratos, herança, identidade | `ObjectMethods`, `AbstractVsInterface`, `EqualsHashCodeContract`, `ComparableComparator` |
| `com.estudos.core.generics_collections` | Genéricos + coleções | `GenericsBasics`, `CollectionsOverview` |
| `com.estudos.core.declarative` | Estilo declarativo (streams) | `DeclarativeVsImperative` |
| `com.estudos.core.jvm` | JVM: memória, GC | `JvmMemoryModelIntro`, `GarbageCollectorBasics` |
| `com.estudos.core.concurrency` | Threads e memória compartilhada | `MultithreadingIntro` |

## Mapa rápido (consulta por pergunta)

| Pergunta | Onde ver |
|----------|----------|
| `==` vs `equals` em `String` | `imperative.StringImmutability` |
| Java é pass-by-reference? | `imperative.PassByValue` |
| Primitivo vs referência / onde mora o dado | `jvm.JvmMemoryModelIntro` |
| Checked vs unchecked | `exceptions.ExceptionsHierarchy` |
| `equals`/`hashCode` e `HashMap` | `oop.EqualsHashCodeContract` |
| `synchronized` / contador seguro | `concurrency.MultithreadingIntro` |
| O que é garbage collector? | `jvm.GarbageCollectorBasics` |
| Imperativo vs stream | `declarative.DeclarativeVsImperative` |

## Testes automatizados

```bash
mvn test
```

- **JUnit 5** + **AssertJ** (asserções fluentes) nos desafios em `challenges.*`.
- **Mockito** em `src/test/java/com/estudos/core/mockito/RelogioMockitoDemoTest.java` (exemplo isolado).

## Executar exemplos

```bash
cd core
mvn -q compile exec:java "-Dexec.mainClass=com.estudos.core.imperative.StringImmutability"
```

**PowerShell:** mantenha aspas no `-Dexec.mainClass=...`.

## Desafios (`com.estudos.core.challenges.*`)

O código-fonte dos desafios contém **soluções de referência** para `mvn test` passar (ex.: comente `Quebrado#hashCode` em `ChallengeEqualsHashCode` para reproduzir o bug descrito no Javadoc).

| Pacote | Classe | Habilidade |
|--------|--------|------------|
| `challenges.collections` | `ChallengeAnagram` | letras / ordenação |
| `challenges.oop` | `ChallengeEqualsHashCode` | contrato em `HashMap` |
| `challenges.concurrency` | `ChallengeThreadSafeCounter` | `synchronized` / `AtomicInteger` |

## Leitura recomendada

- [Java Language Specification](https://docs.oracle.com/javase/specs/jls/se17/html/)
- [JVM Specification](https://docs.oracle.com/javase/specs/jvms/se17/html/)
