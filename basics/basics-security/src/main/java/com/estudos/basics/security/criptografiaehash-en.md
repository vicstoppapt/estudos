# Hash, cryptography, and TLS (concepts)

**PT:** [criptografiaehash.md](criptografiaehash.md)

Part of **`basics-security`**. Hash example: [`CryptoLiteracy.java`](CryptoLiteracy.java).

---

## 1. Hash (digest)

**One-way** function (in practice): fixed-size output; you **do not** recover the input from the digest. Uses:

- **Integrity** (file unchanged) — compare digest.
- **Password** — **do not** use plain SHA-256; use **bcrypt / Argon2 / PBKDF2** with **salt** and computational cost.

---

## 2. Symmetric vs asymmetric cryptography

| | **Symmetric** (AES, ChaCha20) | **Asymmetric** (RSA, ECDSA, ECDH) |
|---|-------------------------------|-----------------------------------|
| Keys | One shared key | Public/private pair |
| Speed | Fast for large volumes | Heavier |
| Typical use | Data at rest or TLS session | Key exchange, signatures |

**TLS** combines: handshake with **asymmetric** to agree a **symmetric** session key for traffic.

---

## 3. What not to do

- Invent protocols or “encrypt” with XOR.
- Store passwords in clear or only with MD5/SHA1 without salt.
- Hardcode keys in Git.

Use reviewed libraries and standards (TLS 1.2+, AEAD).

---

## 4. Encryption at rest

Disks, databases, *object storage*: **encryption** with managed keys (KMS, HSM). Separate **who** accesses data from **who** manages keys.

Next: [segredosowasp-en.md](segredosowasp-en.md).
