package com.estudos.core.oop;

/**
 * Herança simples + interface. Teoria: README.md.
 */
public final class AbstractVsInterface {

    private AbstractVsInterface() {
    }

    // Contrato de movimento; implementação fica nas classes concretas
    interface Deslocavel {
        void mover();
    }

    // Estado comum (id) + hook abstrato ligar()
    abstract static class Veiculo {
        protected final String id;

        protected Veiculo(String id) {
            this.id = id;
        }

        abstract void ligar();
    }

    static final class Bicicleta extends Veiculo implements Deslocavel {
        Bicicleta(String id) {
            super(id); // inicializa id na superclasse
        }

        @Override
        void ligar() {
            System.out.println(id + ": sem motor");
        }

        @Override
        public void mover() {
            System.out.println(id + ": pedalando");
        }
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Bicicleta b = new Bicicleta("B1");
        b.ligar(); // método abstrato resolvido na subclasse
        b.mover(); // interface Deslocavel

        // meuPlayground();
    }
}
