package com.estudos.basics.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * PT: Ficheiros locais via {@link java.nio.file} (JDK standard). Teoria: {@code jdbcfilesobjectstorage.md}.
 * EN: Local files via {@link java.nio.file} (JDK standard). Theory: {@code jdbcfilesobjectstorage.md}.
 */
public final class StorageLiteracy {

    private StorageLiteracy() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) throws IOException {
        Path tmp = Path.of(System.getProperty("java.io.tmpdir"));
        System.out.println("PT: tmpdir existe e é diretório? " + Files.isDirectory(tmp));
        System.out.println("EN: tmpdir exists and is directory? " + Files.isDirectory(tmp));
        System.out.println("PT: espaço livre (bytes, best effort): " + tmp.toFile().getUsableSpace());
        System.out.println("EN: free space (bytes, best effort): " + tmp.toFile().getUsableSpace());

        // meuPlayground();
    }
}
