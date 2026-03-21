package com.estudos.core.exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Checked, unchecked, finally. Teoria: README.md.
 */
public final class ExceptionsHierarchy {

    private ExceptionsHierarchy() {
    }

    static int finallySempreRoda() {
        try {
            return 1;
        } finally {
            // Executa antes de devolver o valor do return acima
            System.out.println("finally executa antes do return");
        }
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        System.out.println("retorno: " + finallySempreRoda());

        // IOException é checked — precisa catch ou throws
        try {
            Files.readString(Path.of("arquivo-inexistente-xyz.txt"));
        } catch (IOException e) {
            System.out.println("IOException (checked): " + e.getClass().getSimpleName());
        }

        // ArithmeticException é RuntimeException — unchecked
        try {
            int x = 1 / 0;
        } catch (ArithmeticException e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }

        // meuPlayground();
    }
}
