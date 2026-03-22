package com.estudos.basics.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Ficheiros locais via {@link java.nio.file} (JDK standard). Teoria: {@code jdbcfilesobjectstorage.md}.
 */
public final class StorageLiteracy {

    private StorageLiteracy() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) throws IOException {
        Path tmp = Path.of(System.getProperty("java.io.tmpdir"));
        System.out.println("tmpdir existe e é diretório? " + Files.isDirectory(tmp));
        System.out.println("espaço livre (bytes, best effort): " + tmp.toFile().getUsableSpace());

        // meuPlayground();
    }
}
