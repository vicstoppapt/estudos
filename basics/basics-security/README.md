# `basics-security` — autenticação, criptografia, riscos

**English:** [README-en.md](README-en.md)

**Repo `estudos`:** [DOCUMENTATION.md](../../DOCUMENTATION.md) · [GLOSSARY.md](../../GLOSSARY.md).

Submódulo do agregador `basics`: **segurança para arquitetos** (não substitui formação OWASP/CISM nem auditorias). Liga com **`basics-networking`** (TLS, HTTPS).

## Documentos

| Ficheiro | Tema |
|----------|------|
| [autenticacaoautorizacao.md](src/main/java/com/estudos/basics/security/autenticacaoautorizacao.md) | AuthN vs AuthZ, sessões, JWT, OAuth2/OpenID em alto nível |
| [criptografiaehash.md](src/main/java/com/estudos/basics/security/criptografiaehash.md) | Hash, simétrico vs assimétrico, TLS, o que **não** inventar |
| [segredosowasp.md](src/main/java/com/estudos/basics/security/segredosowasp.md) | Gestão de segredos, OWASP Top 10 (mapa), supply chain |
| [defesasprevencao.md](src/main/java/com/estudos/basics/security/defesasprevencao.md) | CIA, defesa em profundidade, tipos de controlo, princípios |
| [engenhariasocialgolpes.md](src/main/java/com/estudos/basics/security/engenhariasocialgolpes.md) | Engenharia social, *scams*, prevenção |
| [injecaosqleddos.md](src/main/java/com/estudos/basics/security/injecaosqleddos.md) | SQL injection e DDoS — exemplos e defesas |

## Código

| Classe | `exec:mainClass` |
|--------|------------------|
| `CryptoLiteracy` | `com.estudos.basics.security.CryptoLiteracy` |
| `SqlInjectionConcept` | `com.estudos.basics.security.SqlInjectionConcept` |

`CryptoLiteracy`: hash só para **integridade**; não uses como *password hashing*. `SqlInjectionConcept`: saída textual educativa (sem ligar a BD).

## Maven

```bash
cd basics
mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.CryptoLiteracy"
mvn -q exec:java -pl basics-security "-Dexec.mainClass=com.estudos.basics.security.SqlInjectionConcept"
```
