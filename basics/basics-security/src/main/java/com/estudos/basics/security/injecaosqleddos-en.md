# SQL injection, DDoS, and defenses (with examples)

**PT:** [injecaosqleddos.md](injecaosqleddos.md)

**`basics-security`**. JDBC and data: `basics-storage` → [jdbcfilesobjectstorage-en.md](../../../../../../../../basics-storage/src/main/java/com/estudos/basics/storage/jdbcfilesobjectstorage-en.md). Illustrative Java example: [`SqlInjectionConcept.java`](SqlInjectionConcept.java).

---

## 1. SQL injection (SQLi)

### 1.1 Idea

The application **concatenates** user input into a **SQL string**. The attacker injects **SQL syntax** that changes query meaning (ignore *password*, read other tables, delete data).

### 1.2 Classic example (anti-pattern — never in production)

Conceptual code (pseudo):

```sql
SELECT id, name FROM users WHERE name = '<userInput>' AND password = '<pass>';
```

If `userInput` is (with quotes as the attacker intends):

```text
' OR '1'='1' --
```

The query becomes logically something like: condition **always true** + comment that nullifies the rest:

```sql
SELECT id, name FROM users WHERE name = '' OR '1'='1' -- ' AND password = '...'
```

Result: **authentication bypass** or **data exposure**, depending on the query.

### 1.3 Other variants (map)

| Variant | Idea |
|---------|------|
| **Union-based** | `UNION SELECT` to pull columns from other tables |
| **Blind (boolean/time)** | Infer data from response difference or delay (`SLEEP`) |
| **Second-order** | Input stored today, executed in another flow later |

### 1.4 Defenses (preference order)

1. **`PreparedStatement` / bound parameters** — engine treats values as **data**, not SQL. In JPA, use **named parameters**, not concatenation in native `@Query`.
2. **ORM** with parameterized API; **watch** dynamic native SQL and `createNativeQuery` + string.
3. **Least privilege** on DB: app does not need `DROP`, `FILE`, or `SELECT` on irrelevant tables.
4. **Input validation** (type, size, *allowlist*) — **complement**, not a substitute for *parameterization*.
5. **WAF** / IDS rules as **extra** layer, not sole defense.
6. **Manual escaping** is **fragile**; avoid as primary strategy.

---

## 2. DDoS (*Distributed Denial of Service*)

### 2.1 Goal

Make the service **unavailable** or **degraded** for legitimate users by saturating **bandwidth**, **state** (TCP connections, app *sockets*), or **logic** (expensive requests).

### 2.2 Forms (scenario examples)

| Type | Example mechanism |
|------|-------------------|
| **Volumetric** | Many Gbps UDP/ICMP reflected traffic (*amplification*) |
| **Protocol** | *SYN flood*, exhausting half-open connection table |
| **Application (L7)** | HTTP GET to heavy URLs; *Slowloris* (slow connections); *login flood* |

**Botnets** distribute origin; **simple IP blocking** is harder.

### 2.3 Defenses (architecture)

1. **CDN / *scrubbing* center** (Cloudflare, Akamai, cloud provider protections) — absorb and filter before *origin*.
2. **Rate limiting** by IP, user, route; *challenge* (CAPTCHA) under suspicion.
3. **Auto-scaling** helps some spikes but **does not** fix *volumetric* larger than the pipe or infinite cost — needs edge mitigation.
4. **Cache** and **static pages** reduce *origin* load.
5. **WAF** with rules for obvious patterns; ASN lists / *geoblock* when business accepts.
6. **Operational plan:** ISP/cloud contact, internal comms, **do not** pay *ransom* to DDoS extortion (illegal and recurring).

### 2.4 DDoS ≠ SQLi

SQLi is **application logic** exploitation; DDoS is **volume** or **state** abuse on network/app. Both can hit **availability** or **data**, but mitigations differ.

---

## 3. Quick code review checklist

- [ ] No SQL built with `+` / `String.format` from HTTP input.  
- [ ] DB credentials not in Docker image or Git.  
- [ ] Public endpoints with *timeout* and request body limit.  
- [ ] *Health checks* separate from heavy work (avoid *LB* hammering expensive routes).

---

## 4. Where to practice in Java

- **`basics-storage`** → JDBC concept.  
- **`java11`** → `HttpClient` (timeouts, headers).  
- OWASP: [SQL Injection](https://owasp.org/www-community/attacks/SQL_Injection), [*Denial of Service* cheat sheet](https://cheatsheetseries.owasp.org/cheatsheets/Denial_of_Service_Cheat_Sheet.html).
