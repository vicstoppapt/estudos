package com.estudos.java21.examples;

/** switch + when. Teoria: README.md. */
public final class SwitchWhenGuard {

    private SwitchWhenGuard() {
    }

    record Pedido(int qtd, double preco) {
    }

    static String classificar(Object o) {
        return switch (o) {
            case Pedido p when p.qtd() <= 0 -> "invalido";
            case Pedido p when p.preco() * p.qtd() > 1000 -> "grande";
            case Pedido p -> "normal";
            default -> "desconhecido";
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
