# JDBC, ficheiros e object storage

**`basics-storage`**. Exemplo local: [`StorageLiteracy.java`](StorageLiteracy.java).

---

## 1. JDBC (Java Database Connectivity)

API **padrão** Java para aceder a bases **SQL** via **driver** (PostgreSQL, MySQL, …).

Conceitos: **DataSource**, **Connection**, **PreparedStatement** (evita injeção SQL se usado com *placeholders*), **ResultSet**, **transações** (`setAutoCommit`, `commit`, `rollback`).

Em aplicações modernas muitas vezes usas **JPA/Hibernate** ou **jOOQ** por cima — mas o JDBC continua por baixo.

**Segurança:** nunca concatenar input de utilizador em SQL cru; alinhar com **`basics-security`**.

---

## 2. Ficheiros locais e partilhados

- **`java.nio.file`**: `Path`, `Files.readString`, *walk* de árvores — exemplos no módulo **`java11`** → [`FilesReadWriteString.java`](../../../../../../../../../java11/src/main/java/com/estudos/java11/examples/FilesReadWriteString.java).
- **NFS / SMB:** ficheiros em rede — atenção a **locks**, **latência** e **consistência** entre nós.
- **Conteúdo binário grande:** stream em vez de carregar tudo para memória.

---

## 3. Object storage (estilo S3)

**Buckets**, **chaves** (paths lógicos), **PUT/GET** via HTTP/API; **durabilidade** e **escala** geridas pelo fornecedor. Versionamento, *lifecycle* (glacier), políticas IAM.

**Quando:** anexos, *backups*, *assets* estáticos, *data lake*. **Não** substitui transações OLTP — combina com metadados em BD.

---

## 4. Replicação e backups

- **RPO/RTO** definem quanto perdes e quanto demoras a recuperar.
- **Backups** imutáveis e testes de **restore** (não só snapshots nunca validados).
