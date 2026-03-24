# ACID, transactions, and CAP

**PT:** [acidcaptransacoes.md](acidcaptransacoes.md)

**`basics-storage`**. Models: [modelosdedados-en.md](modelosdedados-en.md).

---

## 1. ACID (classic relational databases)

| Letter | Meaning |
|--------|---------|
| **A**tomicity | All or nothing — commit or rollback |
| **C**onsistency | Schema / business invariants preserved |
| **I**solation | Concurrent transactions do not interfere invalidly (levels: READ COMMITTED, SERIALIZABLE, …) |
| **D**urability | After commit, data survives failures (WAL, fsync) |

In **microservices**, one business operation may span several DBs — **sagas** or **events** replace a single classic distributed transaction (2PC is rare in modern cloud).

---

## 2. CAP (heuristic, not a “pick one” theorem)

In the presence of a **network partition**, there is a **trade-off** between:

- **C**onsistency (linearizable — everyone sees the same now)
- **A**vailability (requests get responses)
- **P**artition (network split)

In practice: distributed systems choose **consistency models** (strong, eventual, *read-your-writes*) and **SLAs** — not “we are AP or CP” as a slogan.

---

## 3. Eventual consistency

Replicas **converge over time**; reads may be **stale** momentarily. Common in CDNs, DNS, NoSQL replication, caches.

**Architecture:** tell the product whether it **accepts** reading stale data and how to **reconcile** conflicts (version vectors, *last-write-wins* with care).

Next: [jdbcfilesobjectstorage-en.md](jdbcfilesobjectstorage-en.md).
