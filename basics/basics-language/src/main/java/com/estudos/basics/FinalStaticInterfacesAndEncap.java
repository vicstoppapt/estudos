package com.estudos.basics;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * O campo {@code itens} é {@code final}: não podes atribuir outra lista a esse slot.
     * {@link ArrayList#add} muta o <strong>mesmo</strong> objeto {@code ArrayList}; por baixo pode
     * realocar um array interno, mas a identidade do objeto Java (o que {@code itens} aponta) não muda.
     */
    static final class CarrinhoDemonstracao {
        private final List<String> itens = new ArrayList<>();

        void adicionar(String s) {
            itens.add(s);
        }

        int tamanho() {
            return itens.size();
        }

        /** Só para demo: {@code identityHashCode} igual antes/depois de muitos {@code add}. */
        int identidadeDaLista() {
            return System.identityHashCode(itens);
        }
    }

    /** Identificadores e factos de criação imutáveis por instância (vários construtores possíveis). */
    static final class PedidoLogico {
        private final long id;
        private final Instant criadoEm;

        PedidoLogico(long id) {
            this.id = id;
            this.criadoEm = Instant.now();
        }

        PedidoLogico(long id, Instant criadoEm) {
            this.id = id;
            this.criadoEm = criadoEm;
        }

        long getId() {
            return id;
        }

        Instant getCriadoEm() {
            return criadoEm;
        }
    }

    /** Dependência injetada fixa: o mesmo gateway para toda a vida deste objeto. */
    static final class ProcessadorFake {
        private final Runnable gateway;

        ProcessadorFake(Runnable gateway) {
            this.gateway = gateway;
        }

        void executar() {
            gateway.run();
        }
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

        CarrinhoDemonstracao carrinho = new CarrinhoDemonstracao();
        int hashAntes = carrinho.identidadeDaLista();
        for (int i = 0; i < 500; i++) {
            carrinho.adicionar("item-" + i);
        }
        int hashDepois = carrinho.identidadeDaLista();
        System.out.println("carrinho tamanho=" + carrinho.tamanho()
                + " mesma identidade List? " + (hashAntes == hashDepois));

        PedidoLogico p = new PedidoLogico(42L);
        System.out.println("pedido id=" + p.getId() + " criado=" + p.getCriadoEm());

        ProcessadorFake proc = new ProcessadorFake(() -> System.out.println("gateway chamado"));
        proc.executar();

        // meuPlayground();
    }
}
