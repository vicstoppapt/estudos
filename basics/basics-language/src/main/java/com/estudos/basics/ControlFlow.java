package com.estudos.basics;

/**
 * PT: if, laços, switch. Teoria: controlflow.md.
 * EN: if, loops, switch. Theory: controlflow.md.
 */
public final class ControlFlow {

    private ControlFlow() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        int n = 7;
        if (n % 2 == 0) {
            System.out.println("PT: par");
            System.out.println("EN: even");
        } else {
            System.out.println("PT: impar");
            System.out.println("EN: odd");
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("for " + i);
        }

        int j = 0;
        while (j < 2) {
            System.out.println("while " + j);
            j++;
        }

        String cor = "verde";
        switch (cor) {
            case "verde" -> {
                System.out.println("PT: siga");
                System.out.println("EN: go");
            }
            case "amarelo" -> {
                System.out.println("PT: atencao");
                System.out.println("EN: caution");
            }
            default -> {
                System.out.println("PT: pare");
                System.out.println("EN: stop");
            }
        }

        // meuPlayground();
    }
}
