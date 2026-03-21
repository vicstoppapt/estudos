package com.estudos.java17.examples;

/** Record básico. Teoria: README.md. */
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
        System.out.println(u); // toString gerado
        System.out.println(u.nome()); // accessor nome()

        // meuPlayground();
    }
}
