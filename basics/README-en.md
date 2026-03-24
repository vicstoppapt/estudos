# Java basics (`java-basics-parent`)

**PT:** [README.md](README.md)

Maven aggregator **before** `core`: submodules with **JDK 17** (`maven.compiler.release` in parent POM) — language, memory, system, networking, **security**, and **storage** (architect view).

**Global plan** (with `core`, `java8`…`java21`): [`estudos/README.md`](../README.md) → **Study plan** section. **Commands for all `estudos` modules:** [`DOCUMENTATION.md`](../DOCUMENTATION.md) · **PT↔EN glossary:** [`GLOSSARY.md`](../GLOSSARY.md).

| Submodule | Artifact | Content |
|-----------|----------|---------|
| [`basics-language`](basics-language/) | `java-basics-language` | Minimal syntax: variables, methods, objects, flow, arrays, API (concept) |
| [`basics-memory`](basics-memory/) | `java-basics-memory` | RAM, stack/heap, CPU cache, references / “pointers” |
| [`basics-os-concurrency`](basics-os-concurrency/) | `java-basics-os-concurrency` | Processor, physical/logical cores, OS/Java threads, pools; JVM vs OS — see `processadorcoresethreads.md` |
| [`basics-networking`](basics-networking/) | `java-basics-networking` | TCP/UDP, DNS, HTTP/REST, gRPC, WebSocket, queues… |
| [`basics-security`](basics-security/) | `java-basics-security` | AuthN/Z, hash/crypto (concept), secrets, OWASP |
| [`basics-storage`](basics-storage/) | `java-basics-storage` | Data models, ACID/CAP, JDBC, files, object storage |

**Language index:** [`basics-language/src/main/java/com/estudos/basics/README-en.md`](basics-language/src/main/java/com/estudos/basics/README-en.md).

## Suggested order — “architect / systems” track

1. **Language** — table below (or only gaps).
2. **Memory** — [`basics-memory/README-en.md`](basics-memory/README-en.md) (in parallel with `VariablesAndTypes` if you like).
3. **OS and CPU** — [`basics-os-concurrency/README.md`](basics-os-concurrency/README.md); PT guide: [`processadorcoresethreads.md`](basics-os-concurrency/src/main/java/com/estudos/basics/os/processadorcoresethreads.md) · EN: [`processadorcoresethreads-en.md`](basics-os-concurrency/src/main/java/com/estudos/basics/os/processadorcoresethreads-en.md) · glossary: [`glossary-os-concurrency.md`](basics-os-concurrency/src/main/java/com/estudos/basics/os/glossary-os-concurrency.md).
4. **Networking** — [`basics-networking/README-en.md`](basics-networking/README-en.md); then HTTP in code in **`java11`**.
5. **Security** — [`basics-security/README-en.md`](basics-security/README-en.md) (complements HTTPS/TLS from networking).
6. **Storage** — [`basics-storage/README-en.md`](basics-storage/README-en.md).

## Suggested order — language only (table)

| # | Class (`exec:mainClass`) | Topic |
|---|--------------------------|--------|
| 1 | `com.estudos.basics.VariablesAndTypes` | primitives vs reference, `==`/`equals`, `null`, wrappers |
| 2 | `com.estudos.basics.MethodsAndParameters` | signature, return, overload |
| 3 | `com.estudos.basics.ObjectsConstructorsThis` | `new`, constructor, `this` |
| 4 | `com.estudos.basics.StaticMembers` | `static` |
| 5 | `com.estudos.basics.FinalStaticInterfacesAndEncap` | `final`, `interface`, encapsulation |
| 6 | `com.estudos.basics.ControlFlow` | `if`, loops, `switch` |
| 7 | `com.estudos.basics.ArraysAndNull` | arrays, `null` |

**Pointers / references:** [pointersreferencesdeepdive-en.md](basics-memory/src/main/java/com/estudos/basics/memory/pointersreferencesdeepdive-en.md).

## Maven (`basics` folder)

```bash
cd basics
mvn -q compile
```

This compiles **all** submodules (includes `basics-os-concurrency`). Extra commands and variants (single submodule, PT/EN): [`basics-os-concurrency/README.md`](basics-os-concurrency/README.md).

Examples:

```bash
mvn -q exec:java -pl basics-language "-Dexec.mainClass=com.estudos.basics.VariablesAndTypes"
mvn -q exec:java -pl basics-memory "-Dexec.mainClass=com.estudos.basics.memory.ReferencesAndPointers"
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.ProcessorThreadExamples"
mvn -q exec:java -pl basics-os-concurrency "-Dexec.mainClass=com.estudos.basics.os.OsRuntimeSnapshot"
mvn -q exec:java -pl basics-networking "-Dexec.mainClass=com.estudos.basics.network.NetworkingLiteracy"
mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.CryptoLiteracy"
mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.SqlInjectionConcept"
mvn -q exec:java -pl basics-storage "-Dexec.mainClass=com.estudos.basics.storage.StorageLiteracy"
```

**PowerShell:** keep quotes around `-Dexec.mainClass=...`.

## Next

Continue to `../core` (`core/README.md` — numbered track). **Frameworks / Spring Boot:** `../frameworks` (`frameworks/README.md`).
