package com.estudos.core.imperative;

/**
 * PT: Demonstra passagem por valor. Teoria: README.md.
 * EN: Demonstrates pass-by-value. Theory: README.md.
 */
public final class PassByValue {

    private PassByValue() {
    }

    // PT: Parâmetro sb é uma cópia da referência. sb = new ... só altera essa cópia; o StringBuilder do chamador não muda.
    // EN: Parameter sb is a copy of the reference. sb = new ... only changes that copy; the caller's StringBuilder is unchanged.
    static void tentarTrocarReferencia(StringBuilder sb) {
        sb = new StringBuilder("outro");
    }

    // PT: Mesmo objeto no heap que o chamador; append altera estado compartilhado.
    // EN: Same heap object as the caller; append mutates shared state.
    static void mutarConteudo(StringBuilder sb) {
        sb.append(" mundo");
    }

    // PT: x é cópia do int; x++ não volta para quem chamou.
    // EN: x is a copy of the int; x++ does not affect the caller.
    static void incrementar(int x) {
        x++;
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: 1) a continua "ola" — a referência em tentarTrocarReferencia não vaza para fora.
        // EN: 1) a stays "ola" — the reference inside tentarTrocarReferencia does not leak out.
        StringBuilder a = new StringBuilder("ola");
        tentarTrocarReferencia(a);
        System.out.println("apos tentarTrocarReferencia: " + a);

        // PT: 2) mesmo objeto a — append visível aqui.
        // EN: 2) same object a — append visible here.
        mutarConteudo(a);
        System.out.println("apos mutarConteudo: " + a);

        // PT: 3) n permanece 1 após incrementar(n).
        // EN: 3) n stays 1 after incrementar(n).
        int n = 1;
        incrementar(n);
        System.out.println("apos incrementar: " + n);

        // meuPlayground();
    }
}
