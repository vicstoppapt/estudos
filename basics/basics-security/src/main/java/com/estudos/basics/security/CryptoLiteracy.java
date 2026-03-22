package com.estudos.basics.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * Hash criptográfico só para **ilustrar** integridade de dados (ex.: verificar ficheiro).
 * **Não** serve como armazenamento de password de utilizador — aí usam-se algoritmos lentos
 * (bcrypt, Argon2, PBKDF2) com salt. Teoria: {@code criptografiaehash.md}.
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

        // meuPlayground();
    }
}
