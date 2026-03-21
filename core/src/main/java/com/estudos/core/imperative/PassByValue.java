package com.estudos.core.imperative;

/**
 * Demonstra passagem por valor. Teoria: README.md.
 */
public final class PassByValue {

    private PassByValue() {
    }

    // Parâmetro sb é uma cópia da referência. sb = new ... só altera essa cópia; o StringBuilder do chamador não muda.
    static void tentarTrocarReferencia(StringBuilder sb) {
        sb = new StringBuilder("outro");
    }

    // Mesmo objeto no heap que o chamador; append altera estado compartilhado.
    static void mutarConteudo(StringBuilder sb) {
        sb.append(" mundo");
    }

    // x é cópia do int; x++ não volta para quem chamou.
    static void incrementar(int x) {
        x++;
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // 1) a continua "ola" — a referência em tentarTrocarReferencia não vaza para fora
        StringBuilder a = new StringBuilder("ola");
        tentarTrocarReferencia(a);
        System.out.println("apos tentarTrocarReferencia: " + a);

        // 2) mesmo objeto a — append visível aqui
        mutarConteudo(a);
        System.out.println("apos mutarConteudo: " + a);

        // 3) n permanece 1 após incrementar(n)
        int n = 1;
        incrementar(n);
        System.out.println("apos incrementar: " + n);

        // meuPlayground();
    }
}
