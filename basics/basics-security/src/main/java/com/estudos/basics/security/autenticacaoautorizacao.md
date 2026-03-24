# Autenticação vs autorização

**English:** [autenticacaoautorizacao-en.md](autenticacaoautorizacao-en.md)

Submódulo **`basics-security`**. Rede e TLS: `basics-networking` → [outrosprotocolos.md](../../../../../../../../basics-networking/src/main/java/com/estudos/basics/network/outrosprotocolos.md).

---

## 1. AuthN e AuthZ

| Termo | Significado | Exemplo |
|--------|-------------|---------|
| **Autenticação (AuthN)** | Provar **quem** és | Login com password, certificado cliente, SSO |
| **Autorização (AuthZ)** | O que **podes** fazer | Papel `admin` vs `leitor`, scopes OAuth |

Confundir os dois leva a APIs que “confiam” só porque o pedido veio de dentro da rede (**zero trust** questiona isso).

---

## 2. Sessões vs tokens

- **Sessão no servidor:** cookie opaco (ID de sessão); estado no servidor ou loja partilhada (Redis). Revogação simples; escalar exige *sticky sessions* ou loja central.
- **JWT (JSON Web Token)** assinado: estado **no token**; serviços stateless validam assinatura. **Revogação** e **rotação** são mais trabalhosas; tokens longos aumentam risco se vazarem.

---

## 3. OAuth2 e OpenID Connect (mapa)

- **OAuth2:** *delegação de acesso* (ex.: app acede ao calendário com consentimento do utilizador). Não é “login” por si só.
- **OpenID Connect:** camada de **identidade** em cima de OAuth2 — *id_token*, claims padronizadas.

Em arquitetura: **Authorization Server** vs **Resource Server** vs **Client**; fluxos (*authorization code* com PKCE para SPAs/mobile).

---

## 4. Boas práticas de desenho

- **HTTPS** em trânsito; **HSTS** onde fizer sentido.
- **Princípio do menor privilégio** em APIs e bases de dados.
- **Não** colocar segredos em repositório — ver [segredosowasp.md](segredosowasp.md).

**Humano e processos:** [engenhariasocialgolpes.md](engenhariasocialgolpes.md) (golpes, MFA, verificação de pagamentos).

Seguinte: [criptografiaehash.md](criptografiaehash.md).
