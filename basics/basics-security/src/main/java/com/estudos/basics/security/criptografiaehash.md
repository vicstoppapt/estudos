# Hash, criptografia e TLS (conceitos)

Parte de **`basics-security`**. Exemplo de hash: [`CryptoLiteracy.java`](CryptoLiteracy.java).

---

## 1. Hash (digest)

Função **unidirecional** (na prática): produz saída de tamanho fixo; **não** recuperas o input a partir do digest. Usos:

- **Integridade** (ficheiro não foi alterado) — comparar digest.
- **Password** — **não** uses SHA-256 “puro”; usa **bcrypt / Argon2 / PBKDF2** com **salt** e custo computacional.

---

## 2. Criptografia simétrica vs assimétrica

| | **Simétrica** (AES, ChaCha20) | **Assimétrica** (RSA, ECDSA, ECDH) |
|---|-------------------------------|-------------------------------------|
| Chaves | Uma chave partilhada | Par público/privado |
| Velocidade | Rápida para volumes grandes | Mais pesada |
| Uso típico | Dados em repouso ou sessão TLS | Troca de chaves, assinaturas |

**TLS** combina: handshake com **assimétrico** para acordar chave de sessão **simétrica** para o tráfego.

---

## 3. O que não fazer

- Inventar protocolos ou “cifrar” com XOR.
- Guardar passwords em claro ou só com MD5/SHA1 sem salt.
- Hardcodar chaves no Git.

Usa bibliotecas e padrões revistos (TLS 1.2+, AEAD).

---

## 4. Encriptação em repouso

Discos, bases de dados, *object storage*: **encriptação** com chaves geridas (KMS, HSM). Separa **quem** acede aos dados de **quem** gere as chaves.

Seguinte: [segredosowasp.md](segredosowasp.md).
