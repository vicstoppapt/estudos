package com.estudos.java21.examples;

/** Record pattern no switch. Teoria: README.md. */
public final class RecordPatternsSwitch {

    private RecordPatternsSwitch() {
    }

    record Ponto(int x, int y) {
    }

    static int soma(Object o) {
        return switch (o) {
            case Ponto(int x, int y) -> x + y; // decomposição do record
            default -> 0;
        };
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        System.out.println(soma(new Ponto(2, 3)));
        System.out.println(soma("x"));

        // meuPlayground();
    }
}
