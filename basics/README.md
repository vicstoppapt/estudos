# Java basics (`java-basics-parent`)

Agregador Maven **anterior** ao `core`: submódulos com **JDK 17** (`maven.compiler.release` no POM pai) — linguagem, memória, sistema, rede, **segurança** e **armazenamento** (visão de arquiteto).

**Plano global** (com `core`, `java8`…`java21`): [`estudos/README.md`](../README.md) → secção **Plano de estudos**.

| Submódulo | Artefacto | Conteúdo |
|-----------|-----------|----------|
| [`basics-language`](basics-language/) | `java-basics-language` | Sintaxe mínima: variáveis, métodos, objetos, fluxo, arrays, API (conceito) |
| [`basics-memory`](basics-memory/) | `java-basics-memory` | RAM, stack/heap, cache CPU, referências / “ponteiros” |
| [`basics-os-concurrency`](basics-os-concurrency/) | `java-basics-os-concurrency` | Processo SO, threads, núcleos, agendamento, JVM vs SO |
| [`basics-networking`](basics-networking/) | `java-basics-networking` | TCP/UDP, DNS, HTTP/REST, gRPC, WebSocket, filas… |
| [`basics-security`](basics-security/) | `java-basics-security` | AuthN/Z, hash/criptografia (conceito), segredos, OWASP |
| [`basics-storage`](basics-storage/) | `java-basics-storage` | Modelos de dados, ACID/CAP, JDBC, ficheiros, object storage |

**Índice linguagem:** [`basics-language/src/main/java/com/estudos/basics/README.md`](basics-language/src/main/java/com/estudos/basics/README.md).

## Ordem sugerida — trilha “arquiteto / sistema”

1. **Linguagem** — tabela abaixo (ou só o que faltar).
2. **Memória** — [`basics-memory/README.md`](basics-memory/README.md) (em paralelo com `VariablesAndTypes` se quiseres).
3. **SO e CPU** — [`basics-os-concurrency/README.md`](basics-os-concurrency/README.md).
4. **Rede** — [`basics-networking/README.md`](basics-networking/README.md); depois HTTP em código no **`java11`**.
5. **Segurança** — [`basics-security/README.md`](basics-security/README.md) (complementa HTTPS/TLS da rede).
6. **Armazenamento** — [`basics-storage/README.md`](basics-storage/README.md).

## Ordem sugerida — apenas linguagem (tabela)

| # | Classe (`exec:mainClass`) | Tema |
|---|---------------------------|------|
| 1 | `com.estudos.basics.VariablesAndTypes` | primitivos vs referência, `==`/`equals`, `null`, wrappers |
| 2 | `com.estudos.basics.MethodsAndParameters` | assinatura, retorno, sobrecarga |
| 3 | `com.estudos.basics.ObjectsConstructorsThis` | `new`, construtor, `this` |
| 4 | `com.estudos.basics.StaticMembers` | `static` |
| 5 | `com.estudos.basics.FinalStaticInterfacesAndEncap` | `final`, `interface`, encapsulamento |
| 6 | `com.estudos.basics.ControlFlow` | `if`, laços, `switch` |
| 7 | `com.estudos.basics.ArraysAndNull` | arrays, `null` |

**Ponteiros / referências:** [pointersreferencesdeepdive.md](basics-memory/src/main/java/com/estudos/basics/memory/pointersreferencesdeepdive.md).

## Maven (pasta `basics`)

```bash
cd basics
mvn -q compile
```

Exemplos:

```bash
mvn -q exec:java -pl basics-language "-Dexec.mainClass=com.estudos.basics.VariablesAndTypes"
mvn -q exec:java -pl basics-memory "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
mvn -q exec:java -pl basics-networking "-Dexec.mainClass=com.estudos.basics.network.NetworkingLiteracy"
mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.CryptoLiteracy"
mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.SqlInjectionConcept"
mvn -q exec:java -pl basics-storage "-Dexec.mainClass=com.estudos.basics.storage.StorageLiteracy"
```

**PowerShell:** mantenha aspas no `-Dexec.mainClass=...`.

## Depois

Seguir para `../core` (`core/README.md` — trilha numerada). **Frameworks / Spring Boot:** módulo `../frameworks` (`frameworks/README.md`).
