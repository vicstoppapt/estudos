package com.estudos.java17.examples;

/**
 * PT: Record básico. Teoria: README.md.
 * EN: Basic record. Theory: README.md.
 */
public final class RecordsExample {

    private RecordsExample() {
    }

    record Usuario(String nome, int id) {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Usuario u = new Usuario("Ana", 1);
        // PT: toString gerado
        // EN: generated toString
        System.out.println(u);
        // PT: accessor nome()
        // EN: accessor nome()
        System.out.println(u.nome());

        // meuPlayground();
    }
}
