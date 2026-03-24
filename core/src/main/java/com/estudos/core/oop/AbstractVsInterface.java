package com.estudos.core.oop;

/**
 * PT: Herança simples + interface. Teoria: README.md.
 * EN: Simple inheritance + interface. Theory: README.md.
 */
public final class AbstractVsInterface {

    private AbstractVsInterface() {
    }

    // PT: Contrato de movimento; implementação fica nas classes concretas.
    // EN: Movement contract; implementation lives in concrete classes.
    interface Deslocavel {
        void mover();
    }

    // PT: Estado comum (id) + hook abstrato ligar().
    // EN: Shared state (id) + abstract hook ligar().
    abstract static class Veiculo {
        protected final String id;

        protected Veiculo(String id) {
            this.id = id;
        }

        abstract void ligar();
    }

    static final class Bicicleta extends Veiculo implements Deslocavel {
        Bicicleta(String id) {
            // PT: Inicializa id na superclasse.
            // EN: Initializes id in the superclass.
            super(id);
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

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Bicicleta b = new Bicicleta("B1");
        // PT: Método abstrato resolvido na subclasse.
        // EN: Abstract method resolved in the subclass.
        b.ligar();
        // PT: Interface Deslocavel.
        // EN: Deslocavel interface.
        b.mover();

        // meuPlayground();
    }
}
