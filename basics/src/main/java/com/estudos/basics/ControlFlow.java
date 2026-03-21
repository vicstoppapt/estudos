package com.estudos.basics;

/**
 * if, laços, switch. Teoria: controlflow.md.
 */
public final class ControlFlow {

    private ControlFlow() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        int n = 7;
        if (n % 2 == 0) {
            System.out.println("par");
        } else {
            System.out.println("impar");
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
            case "verde" -> System.out.println("siga");
            case "amarelo" -> System.out.println("atencao");
            default -> System.out.println("pare");
        }

        // meuPlayground();
    }
}
