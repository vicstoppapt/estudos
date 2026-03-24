package com.estudos.core.exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * PT: Checked, unchecked, finally. Teoria: README.md.
 * EN: Checked, unchecked, finally. Theory: README.md.
 */
public final class ExceptionsHierarchy {

    private ExceptionsHierarchy() {
    }

    static int finallySempreRoda() {
        try {
            return 1;
        } finally {
            // PT: Executa antes de devolver o valor do return acima.
            // EN: Runs before returning the value from the return above.
            System.out.println("finally executa antes do return");
        }
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        System.out.println("retorno: " + finallySempreRoda());

        // PT: IOException é checked — precisa catch ou throws.
        // EN: IOException is checked — requires catch or throws.
        try {
            Files.readString(Path.of("arquivo-inexistente-xyz.txt"));
        } catch (IOException e) {
            System.out.println("IOException (checked): " + e.getClass().getSimpleName());
        }

        // PT: ArithmeticException é RuntimeException — unchecked.
        // EN: ArithmeticException is RuntimeException — unchecked.
        try {
            int x = 1 / 0;
        } catch (ArithmeticException e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }

        // meuPlayground();
    }
}
