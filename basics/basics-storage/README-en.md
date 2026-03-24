# `basics-storage` — data, transactions, files

**PT:** [README.md](README.md)

**Repo `estudos`:** [DOCUMENTATION.md](../../DOCUMENTATION.md) · [GLOSSARY.md](../../GLOSSARY.md).

Submodule of the `basics` aggregator: **storage** from an architect’s view (models, consistency, JDBC concept, files vs object).

## Documents

| File | Topic |
|------|--------|
| [modelosdedados-en.md](src/main/java/com/estudos/basics/storage/modelosdedados-en.md) | Relational, document, key-value, columnar, graph |
| [acidcaptransacoes-en.md](src/main/java/com/estudos/basics/storage/acidcaptransacoes-en.md) | ACID, transactions, CAP as heuristic, eventual consistency |
| [jdbcfilesobjectstorage-en.md](src/main/java/com/estudos/basics/storage/jdbcfilesobjectstorage-en.md) | JDBC in Java, local files, object storage (S3-like) |

## Code

| Class | `exec:mainClass` |
|--------|------------------|
| `StorageLiteracy` | `com.estudos.basics.storage.StorageLiteracy` |

## Maven

```bash
cd basics
mvn -q exec:java -pl basics-storage "-Dexec.mainClass=com.estudos.basics.storage.StorageLiteracy"
```
