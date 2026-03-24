package com.estudos.java17.examples;

/**
 * PT: switch expressão + yield. Teoria: README.md.
 * EN: switch expression + yield. Theory: README.md.
 */
public final class SwitchExpressions {

    private SwitchExpressions() {
    }

    static String dia(int d) {
        return switch (d) {
            case 1, 7 -> "PT: fim de semana | EN: weekend";
            case 2, 3, 4, 5, 6 -> {
                // PT: Bloco precisa de yield para produzir valor da expressão
                // EN: A block must use yield to produce the expression value
                yield "PT: dia util | EN: weekday";
            }
            default -> "PT: invalido | EN: invalid";
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
