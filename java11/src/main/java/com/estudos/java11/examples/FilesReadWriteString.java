package com.estudos.java11.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** readString/writeString. Teoria: README.md. */
public final class FilesReadWriteString {

    private FilesReadWriteString() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) throws IOException {
        Path p = Files.createTempFile("estudo", ".txt");
        Files.writeString(p, "linha1\nlinha2");
        String conteudo = Files.readString(p); // UTF-8 default
        System.out.println(conteudo);
        Files.deleteIfExists(p);

        // meuPlayground();
    }
}
