package com.estudos.java17.examples;

/** switch expressão + yield. Teoria: README.md. */
public final class SwitchExpressions {

    private SwitchExpressions() {
    }

    static String dia(int d) {
        return switch (d) {
            case 1, 7 -> "fim de semana";
            case 2, 3, 4, 5, 6 -> {
                // Bloco precisa de yield para produzir valor da expressão
                yield "dia util";
            }
            default -> "invalido";
        };
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        System.out.println(dia(3));
        System.out.println(dia(1));

        // meuPlayground();
    }
}
