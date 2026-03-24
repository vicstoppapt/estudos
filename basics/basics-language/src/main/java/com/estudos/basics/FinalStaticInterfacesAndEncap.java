package com.estudos.basics;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * PT: {@code final}, {@code interface}, encapsulamento; {@code static} em profundidade em {@link StaticMembers}.
 * Teoria: finalstaticinterfacesandencap.md.
 * EN: {@code final}, {@code interface}, encapsulation; {@code static} in depth in {@link StaticMembers}.
 * Theory: finalstaticinterfacesandencap.md.
 */
public final class FinalStaticInterfacesAndEncap {

    private FinalStaticInterfacesAndEncap() {
    }

    /**
     * PT: Constante de classe: um valor compartilhado, convenção MAIÚSCULAS.
     * EN: Class constant: a shared value, UPPER_SNAKE naming convention.
     */
    public static final String MODULO = "basics";

    /**
     * PT: Contrato: quem implementa deve definir {@code play}; {@code default} já vem pronto.
     * EN: Contract: implementors must define {@code play}; {@code default} is already provided.
     */
    interface Reprodutor {
        void play(String faixa);

        default void playSilencioso(String faixa) {
            System.out.println("(sem som) " + faixa);
            System.out.println("(no sound) " + faixa);
        }
    }

    static final class SpotifyFake implements Reprodutor {
        private String ultima;

        @Override
        public void play(String faixa) {
            ultima = faixa;
            System.out.println("tocando: " + faixa);
            System.out.println("playing: " + faixa);
        }

        String getUltima() {
            return ultima;
        }
    }

    /**
     * PT: Encapsulamento: {@code centavos} só muda por regras definidas aqui.
     * EN: Encapsulation: {@code centavos} only changes through rules defined here.
     */
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
        System.out.println("PT: n=" + n + " dobro=" + dobro);
        System.out.println("EN: n=" + n + " double=" + dobro);
    }

    /**
     * PT: O campo {@code itens} é {@code final}: não podes atribuir outra lista a esse slot.
     * {@link ArrayList#add} muta o <strong>mesmo</strong> objeto {@code ArrayList}; por baixo pode
     * realocar um array interno, mas a identidade do objeto Java (o que {@code itens} aponta) não muda.
     * EN: The {@code itens} field is {@code final}: you cannot assign another list to that slot.
     * {@link ArrayList#add} mutates the <strong>same</strong> {@code ArrayList} object; internally it may
     * reallocate an array, but the Java object identity (what {@code itens} points to) does not change.
     */
    static final class CarrinhoDemonstracao {
        private final List<String> itens = new ArrayList<>();

        void adicionar(String s) {
            itens.add(s);
        }

        int tamanho() {
            return itens.size();
        }

        /**
         * PT: Só para demo: {@code identityHashCode} igual antes/depois de muitos {@code add}.
         * EN: Demo only: same {@code identityHashCode} before/after many {@code add} calls.
         */
        int identidadeDaLista() {
            return System.identityHashCode(itens);
        }
    }

    /**
     * PT: Identificadores e factos de criação imutáveis por instância (vários construtores possíveis).
     * EN: Immutable per-instance identifiers and creation facts (several constructors possible).
     */
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

    /**
     * PT: Dependência injetada fixa: o mesmo gateway para toda a vida deste objeto.
     * EN: Fixed injected dependency: the same gateway for this object’s whole lifetime.
     */
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
        System.out.println("PT: modulo=" + MODULO);
        System.out.println("EN: module=" + MODULO);

        Reprodutor r = new SpotifyFake();
        r.play("jazz");
        r.playSilencioso("pausa");

        ContaSimples c = new ContaSimples();
        c.depositar(100);
        c.depositar(50);
        System.out.println("PT: saldo=" + c.saldoCentavos());
        System.out.println("EN: balance_cents=" + c.saldoCentavos());

        demonstrarParamFinal(7);

        CarrinhoDemonstracao carrinho = new CarrinhoDemonstracao();
        int hashAntes = carrinho.identidadeDaLista();
        for (int i = 0; i < 500; i++) {
            carrinho.adicionar("item-" + i);
        }
        int hashDepois = carrinho.identidadeDaLista();
        System.out.println("PT: carrinho tamanho=" + carrinho.tamanho()
                + " mesma identidade List? " + (hashAntes == hashDepois));
        System.out.println("EN: cart size=" + carrinho.tamanho()
                + " same List identity? " + (hashAntes == hashDepois));

        PedidoLogico p = new PedidoLogico(42L);
        System.out.println("PT: pedido id=" + p.getId() + " criado=" + p.getCriadoEm());
        System.out.println("EN: order id=" + p.getId() + " created=" + p.getCriadoEm());

        ProcessadorFake proc = new ProcessadorFake(() -> {
            System.out.println("PT: gateway chamado");
            System.out.println("EN: gateway invoked");
        });
        proc.executar();

        // meuPlayground();
    }
}
