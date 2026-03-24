package com.estudos.basics.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * PT: Hash criptográfico só para **ilustrar** integridade de dados (ex.: verificar ficheiro).
 * **Não** serve como armazenamento de password de utilizador — aí usam-se algoritmos lentos
 * (bcrypt, Argon2, PBKDF2) com salt. Teoria: {@code criptografiaehash.md}.
 * EN: Cryptographic hash **only** to **illustrate** data integrity (e.g. verify a file).
 * It is **not** for storing user passwords — use slow algorithms (bcrypt, Argon2, PBKDF2) with salt.
 * Theory: {@code criptografiaehash.md}.
 */
public final class CryptoLiteracy {

    private CryptoLiteracy() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest("mesmo input => mesmo digest".getBytes(StandardCharsets.UTF_8));
        System.out.println("SHA-256 (hex): " + HexFormat.of().formatHex(digest));
        System.out.println("PT: entrada fixa em PT ilustra: mesmo input => mesmo digest (determinístico).");
        System.out.println("EN: fixed PT input illustrates: same input ⇒ same digest (deterministic).");

        // meuPlayground();
    }
}
