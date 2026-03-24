# Secrets, OWASP, and supply chain

**PT:** [segredosowasp.md](segredosowasp.md)

**`basics-security`**. Auth: [autenticacaoautorizacao-en.md](autenticacaoautorizacao-en.md).

---

## 1. Secret management

- **Secrets:** DB passwords, API keys, private keys, CI tokens.
- **Where to put them:** environment variables injected by orchestrator, **Vault**, cloud **Secrets Manager**, *sealed secrets* in Kubernetes — **not** versioned files or logs.
- **Rotation:** plan periodic rotation and post-incident rotation.

---

## 2. OWASP Top 10 (mental map, not the full official list)

Categories change by edition; recurring ideas:

| Typical risk | What to architect |
|--------------|-------------------|
| Injection (SQL, command, LDAP) | *Prepared statements*, validation, least privilege on DB |
| Broken authentication | MFA, short sessions, brute-force lockout |
| Sensitive data exposure | TLS, encryption at rest, data minimization |
| XXE / unsafe deserialization | Disable dangerous features, safe formats |
| XSS / CSRF | CSP, anti-CSRF tokens, updated framework |
| Misconfiguration | *Hardening*, minimal images, closed ports |
| Vulnerable components | *Dependency scanning*, SBOM, upgrades |
| Logging / monitoring failures | Do not log secrets; alerts (observability — see main README, next steps) |

List and detail: [OWASP Top 10](https://owasp.org/www-project-top-ten/).

---

## 3. Supply chain

- Maven/npm dependencies with **pinned versions** and **verification** (hashes, *signing* where available).
- **SLSA**, *provenance*, pipelines that **do not** accept PRs without review.

---

## 4. Threat modelling (one sentence)

Identify **assets**, **adversaries**, **attack surfaces**, and **mitigations** before coding at scale — STRIDE, data-flow diagrams.

---

## 5. Deeper dives in this submodule

| File | Topic |
|------|--------|
| [defesasprevencao-en.md](defesasprevencao-en.md) | CIA, defense in depth, preventive/detective/corrective, principles |
| [engenhariasocialgolpes-en.md](engenhariasocialgolpes-en.md) | Phishing, BEC, org and individual prevention |
| [injecaosqleddos-en.md](injecaosqleddos-en.md) | SQL injection (examples), DDoS (types and mitigation), checklist |
| [`SqlInjectionConcept.java`](SqlInjectionConcept.java) | Textual demo *payload* vs `PreparedStatement` |
