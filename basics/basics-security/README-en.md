# `basics-security` — authentication, cryptography, risks

**PT:** [README.md](README.md)

**Repo `estudos`:** [DOCUMENTATION.md](../../DOCUMENTATION.md) · [GLOSSARY.md](../../GLOSSARY.md).

Submodule of the `basics` aggregator: **security for architects** (does not replace OWASP/CISM training or audits). Ties to **`basics-networking`** (TLS, HTTPS).

## Documents

| File | Topic |
|------|--------|
| [autenticacaoautorizacao-en.md](src/main/java/com/estudos/basics/security/autenticacaoautorizacao-en.md) | AuthN vs AuthZ, sessions, JWT, OAuth2/OpenID at a high level |
| [criptografiaehash-en.md](src/main/java/com/estudos/basics/security/criptografiaehash-en.md) | Hash, symmetric vs asymmetric, TLS, what **not** to invent |
| [segredosowasp-en.md](src/main/java/com/estudos/basics/security/segredosowasp-en.md) | Secret management, OWASP Top 10 (map), supply chain |
| [defesasprevencao-en.md](src/main/java/com/estudos/basics/security/defesasprevencao-en.md) | CIA, defense in depth, control types, principles |
| [engenhariasocialgolpes-en.md](src/main/java/com/estudos/basics/security/engenhariasocialgolpes-en.md) | Social engineering, scams, prevention |
| [injecaosqleddos-en.md](src/main/java/com/estudos/basics/security/injecaosqleddos-en.md) | SQL injection and DDoS — examples and defenses |

## Code

| Class | `exec:mainClass` |
|--------|------------------|
| `CryptoLiteracy` | `com.estudos.basics.security.CryptoLiteracy` |
| `SqlInjectionConcept` | `com.estudos.basics.security.SqlInjectionConcept` |

`CryptoLiteracy`: hash for **integrity** only; do not use as *password hashing*. `SqlInjectionConcept`: educational text output (no DB connection).

## Maven

```bash
cd basics
mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.CryptoLiteracy"
mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.SqlInjectionConcept"
```
