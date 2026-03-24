# JDBC, files, and object storage

**PT:** [jdbcfilesobjectstorage.md](jdbcfilesobjectstorage.md)

**`basics-storage`**. Local example: [`StorageLiteracy.java`](StorageLiteracy.java).

---

## 1. JDBC (Java Database Connectivity)

Standard Java **API** to access **SQL** databases via a **driver** (PostgreSQL, MySQL, …).

Concepts: **DataSource**, **Connection**, **PreparedStatement** (avoids SQL injection when used with *placeholders*), **ResultSet**, **transactions** (`setAutoCommit`, `commit`, `rollback`).

Modern apps often use **JPA/Hibernate** or **jOOQ** on top — JDBC remains underneath.

**Security:** never concatenate user input into raw SQL; align with **`basics-security`**.

---

## 2. Local and shared files

- **`java.nio.file`**: `Path`, `Files.readString`, tree *walk* — examples in **`java11`** → [`FilesReadWriteString.java`](../../../../../../../../../java11/src/main/java/com/estudos/java11/examples/FilesReadWriteString.java).
- **NFS / SMB:** network files — mind **locks**, **latency**, and **consistency** across nodes.
- **Large binary content:** stream instead of loading all into memory.

---

## 3. Object storage (S3-style)

**Buckets**, **keys** (logical paths), **PUT/GET** via HTTP/API; **durability** and **scale** managed by the provider. Versioning, *lifecycle* (glacier), IAM policies.

**When:** attachments, *backups*, static *assets*, *data lake*. **Does not** replace OLTP transactions — combine with DB metadata.

---

## 4. Replication and backups

- **RPO/RTO** define how much you lose and how long recovery takes.
- **Immutable backups** and **restore** tests (not only never-validated snapshots).
