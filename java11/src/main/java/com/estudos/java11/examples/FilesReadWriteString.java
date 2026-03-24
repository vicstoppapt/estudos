package com.estudos.java11.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * PT: readString/writeString. Teoria: README.md.
 * EN: readString/writeString. Theory: README.md.
 */
public final class FilesReadWriteString {

    private FilesReadWriteString() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) throws IOException {
        Path p = Files.createTempFile("estudo", ".txt");
        Files.writeString(p, "linha1\nlinha2");
        // PT: UTF-8 default
        // EN: UTF-8 default
        String conteudo = Files.readString(p);
        System.out.println(conteudo);
        Files.deleteIfExists(p);

        // meuPlayground();
    }
}
