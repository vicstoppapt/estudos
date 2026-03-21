package com.estudos.basics;

/**
 * {@code final}, {@code interface}, encapsulamento; {@code static} em profundidade em {@link StaticMembers}.
 * Teoria: finalstaticinterfacesandencap.md.
 */
public final class FinalStaticInterfacesAndEncap {

    private FinalStaticInterfacesAndEncap() {
    }

    /** Constante de classe: um valor compartilhado, convenção MAIÚSCULAS. */
    public static final String MODULO = "basics";

    /** Contrato: quem implementa deve definir {@code play}; {@code default} já vem pronto. */
    interface Reprodutor {
        void play(String faixa);

        default void playSilencioso(String faixa) {
            System.out.println("(sem som) " + faixa);
        }
    }

    static final class SpotifyFake implements Reprodutor {
        private String ultima;

        @Override
        public void play(String faixa) {
            ultima = faixa;
            System.out.println("tocando: " + faixa);
        }

        String getUltima() {
            return ultima;
        }
    }

    /** Encapsulamento: {@code centavos} só muda por regras definidas aqui. */
    static final class ContaSimples {
        private int centavos;

        public void depositar(final int valor) {
            if (valor > 0) {
                centavos += valor;
            }
        }

        public int saldoCentavos() {
            return centavos;
        }
    }

    static void demonstrarParamFinal(final int n) {
        final int dobro = n * 2;
        System.out.println("n=" + n + " dobro=" + dobro);
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        System.out.println("modulo=" + MODULO);

        Reprodutor r = new SpotifyFake();
        r.play("jazz");
        r.playSilencioso("pausa");

        ContaSimples c = new ContaSimples();
        c.depositar(100);
        c.depositar(50);
        System.out.println("saldo=" + c.saldoCentavos());

        demonstrarParamFinal(7);

        // meuPlayground();
    }
}
