# ACID, transações e CAP

**`basics-storage`**. Modelos: [modelosdedados.md](modelosdedados.md).

---

## 1. ACID (bases relacionais clássicas)

| Letra | Significado |
|-------|-------------|
| **A**tomicidade | Tudo ou nada — commit ou rollback |
| **C**onsistência | Invariantes do esquema / negócio mantidas |
| **I**solamento | Transações concorrentes não interferem de formas inválidas (níveis: READ COMMITTED, SERIALIZABLE, …) |
| **D**urabilidade | Após commit, dados sobrevivem a falhas (WAL, fsync) |

Em **microserviços**, uma operação de negócio pode cruzar várias BD — **sagas** ou **eventos** substituem uma única transação distribuída clássica (2PC é raro na nuvem moderna).

---

## 2. CAP (heurística, não “teorema de escolha única”)

Em presença de **partição de rede**, há **trade-off** entre:

- **C**onsistência linearizável (todos veem o mesmo agora)
- **D**isponibilidade (pedidos respondem)
- **P**artição (rede parte)

Na prática: sistemas distribuídos escolhem **modelos de consistência** (forte, eventual, *read-your-writes*) e **SLAs** — não “somos AP ou CP” num slogan.

---

## 3. Consistência eventual

Réplicas convergem **com o tempo**; leituras podem ser **stale** momentaneamente. Comum em CDNs, DNS, replicação NoSQL, caches.

**Arquitetura:** expõe ao produto se **aceita** ler dados antigos e como **reconciliar** conflitos (vetores de versão, *last-write-wins* com cuidado).

Seguinte: [jdbcfilesobjectstorage.md](jdbcfilesobjectstorage.md).
