# Data models and engines

**PT:** [modelosdedados.md](modelosdedados.md)

Submodule **`basics-storage`**. Transactions and CAP: [acidcaptransacoes-en.md](acidcaptransacoes-en.md).

---

## 1. Relational (SQL)

**Tables**, **rows**, rigid **schema**, **JOINs**, **referential integrity** (FK). Strong for **consistency** and ad-hoc reporting (SQL). Examples: PostgreSQL, MySQL, SQL Server, Oracle.

**When:** stable relationships, transactional ACID, team comfortable with SQL.

---

## 2. Document (document store)

**Documents** JSON/BSON by key; flexible schema; queries on indexed fields. E.g. MongoDB, Couchbase, DynamoDB (item model is also close).

**When:** fast schema evolution, natural hierarchical objects; watch **joins** across collections and consistency between documents.

---

## 3. Key-value

**Get/put** by key; very fast; limited query expressiveness. E.g. Redis (also in-memory structures), Riak.

**When:** cache, sessions, light queues, rate limiting.

---

## 4. Columnar / analytical

Optimized for **aggregate reads** on large volumes (OLAP). E.g. BigQuery, Redshift, ClickHouse.

**When:** BI, *data warehouse*, not typical single-row OLTP.

---

## 5. Graph

**Nodes and edges**; neighborhood and path queries. E.g. Neo4j, Neptune.

**When:** social graphs, recommendations, fraud in relationships — do not force a graph into SQL if the domain is genuinely graph-shaped.

---

## 6. Polyglot persistence

Real systems mix **PostgreSQL** + **Redis** + **object storage** + *search* (OpenSearch/Elasticsearch). Each piece has **roles** and **consistency** rules — document **source of truth** and **replication**.

Next: [acidcaptransacoes-en.md](acidcaptransacoes-en.md).
