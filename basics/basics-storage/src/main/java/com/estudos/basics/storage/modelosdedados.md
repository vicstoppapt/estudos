# Modelos de dados e motores

**English:** [modelosdedados-en.md](modelosdedados-en.md)

Submódulo **`basics-storage`**. Transações e CAP: [acidcaptransacoes.md](acidcaptransacoes.md).

---

## 1. Relacional (SQL)

**Tabelas**, **linhas**, **esquema** rígido, **JOINs**, **integridade referencial** (FK). Forte para **consistência** e relatórios ad-hoc (SQL). Exemplos: PostgreSQL, MySQL, SQL Server, Oracle.

**Quando pensa:** dados com relações estáveis, ACID transacional, equipa confortável com SQL.

---

## 2. Documento (document store)

**Documentos** JSON/BSON por chave; esquema flexível; consultas por campos indexados. Ex.: MongoDB, Couchbase, DynamoDB (modelo item também se aproxima).

**Quando pensa:** evolução de esquema rápida, objetos hierárquicos naturais; cuidado com **joins** entre coleções e consistência entre documentos.

---

## 3. Chave-valor

**Get/put** por chave; muito rápido; pouca expressividade de query. Ex.: Redis (também estruturas em memória), Riak.

**Quando pensa:** cache, sessões, filas leves, rate limiting.

---

## 4. Colunar / analítico

Otimizado para **leituras agregadas** em grandes volumes (OLAP). Ex.: BigQuery, Redshift, ClickHouse.

**Quando pensa:** BI, *data warehouse*, não OLTP típico de pedido único.

---

## 5. Grafo

**Nós e arestas**; queries de vizinhança e percursos. Ex.: Neo4j, Neptune.

**Quando pensa:** redes sociais, recomendações, deteção de fraude em relações — não forces grafo em SQL se o domínio é genuinamente gráfico.

---

## 6. Poliglota persistência

Sistemas reais misturam: **PostgreSQL** + **Redis** + **object storage** + *search* (OpenSearch/Elasticsearch). Cada peça tem **papéis** e **consistência** diferentes — documenta **fonte de verdade** e **replicação**.

Seguinte: [acidcaptransacoes.md](acidcaptransacoes.md).
