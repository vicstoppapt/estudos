package com.estudos.java21.examples;

/**
 * PT: switch + when. Teoria: README.md.
 * EN: switch + when guards. Theory: README.md.
 */
public final class SwitchWhenGuard {

    private SwitchWhenGuard() {
    }

    record Pedido(int qtd, double preco) {
    }

    static String classificar(Object o) {
        return switch (o) {
            case Pedido p when p.qtd() <= 0 -> "PT: invalido | EN: invalid";
            case Pedido p when p.preco() * p.qtd() > 1000 -> "PT: grande | EN: large";
            case Pedido p -> "PT: normal | EN: normal";
            default -> "PT: desconhecido | EN: unknown";
        };
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        System.out.println(classificar(new Pedido(2, 600)));
        System.out.println(classificar(new Pedido(0, 10)));

        // meuPlayground();
    }
}
