package com.estudos.java11.challenges;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Leitura UTF-8. README em challenges/. */
public final class ChallengeReadResource {

    private ChallengeReadResource() {
    }

    static String lerUtf8(Path p) throws IOException {
        return Files.readString(p);
    }

    public static void main(String[] args) throws IOException {
        // CWD deve ser a raiz do módulo java11 onde está sample.txt
        Path p = Path.of("sample.txt");
        System.out.println(lerUtf8(p));
    }
}
