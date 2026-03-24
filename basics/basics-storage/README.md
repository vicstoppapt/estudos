# `basics-storage` — dados, transações, ficheiros

**English:** [README-en.md](README-en.md)

**Repo `estudos`:** [DOCUMENTATION.md](../../DOCUMENTATION.md) · [GLOSSARY.md](../../GLOSSARY.md).

Submódulo do agregador `basics`: **armazenamento** em visão de arquiteto (modelos, consistência, JDBC em conceito, ficheiros vs objeto).

## Documentos

| Ficheiro | Tema |
|----------|------|
| [modelosdedados.md](src/main/java/com/estudos/basics/storage/modelosdedados.md) | Relacional, documento, chave-valor, colunar, grafo |
| [acidcaptransacoes.md](src/main/java/com/estudos/basics/storage/acidcaptransacoes.md) | ACID, transações, CAP como heurística, consistência eventual |
| [jdbcfilesobjectstorage.md](src/main/java/com/estudos/basics/storage/jdbcfilesobjectstorage.md) | JDBC em Java, ficheiros locais, object storage (S3-like) |

## Código

| Classe | `exec:mainClass` |
|--------|------------------|
| `StorageLiteracy` | `com.estudos.basics.storage.StorageLiteracy` |

## Maven

```bash
cd basics
mvn -q exec:java -pl basics-storage "-Dexec.mainClass=com.estudos.basics.storage.StorageLiteracy"
```
