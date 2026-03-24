# Authentication vs authorization

**PT:** [autenticacaoautorizacao.md](autenticacaoautorizacao.md)

Submodule **`basics-security`**. Network and TLS: `basics-networking` → [outrosprotocolos-en.md](../../../../../../../../basics-networking/src/main/java/com/estudos/basics/network/outrosprotocolos-en.md).

---

## 1. AuthN and AuthZ

| Term | Meaning | Example |
|------|---------|---------|
| **Authentication (AuthN)** | Prove **who** you are | Login with password, client certificate, SSO |
| **Authorization (AuthZ)** | What you **may** do | Role `admin` vs `reader`, OAuth scopes |

Mixing them leads to APIs that “trust” just because the request came from inside the network (**zero trust** questions that).

---

## 2. Sessions vs tokens

- **Server session:** opaque cookie (session ID); state on server or shared store (Redis). Simple revocation; scaling needs *sticky sessions* or central store.
- **Signed JWT**: state **in the token**; stateless services validate signature. **Revocation** and **rotation** are harder; long-lived tokens increase leak risk.

---

## 3. OAuth2 and OpenID Connect (map)

- **OAuth2:** *delegated access* (e.g. app accesses calendar with user consent). Not “login” by itself.
- **OpenID Connect:** **identity** layer on OAuth2 — *id_token*, standard claims.

In architecture: **Authorization Server** vs **Resource Server** vs **Client**; flows (*authorization code* with PKCE for SPAs/mobile).

---

## 4. Design good practices

- **HTTPS** in transit; **HSTS** where it makes sense.
- **Least privilege** on APIs and databases.
- **Do not** put secrets in the repository — see [segredosowasp-en.md](segredosowasp-en.md).

**People and process:** [engenhariasocialgolpes-en.md](engenhariasocialgolpes-en.md) (scams, MFA, payment verification).

Next: [criptografiaehash-en.md](criptografiaehash-en.md).
