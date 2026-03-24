package com.estudos.java8.examples;

/**
 * PT: default/static em interface. Teoria: README.md.
 * EN: default/static in interfaces. Theory: README.md.
 */
public final class DefaultMethods {

    private DefaultMethods() {
    }

    interface Logger {
        // PT: Implementação padrão — quem implementa a interface herda sem precisar reescrever
        // EN: Default implementation — implementors inherit without overriding
        default void log(String msg) {
            System.out.println("[log] " + msg);
        }

        // PT: Não passa por instância; chamada Logger.header()
        // EN: Not on an instance; call Logger.header()
        static void header() {
            System.out.println("PT: === inicio === | EN: === start ===");
        }
    }

    static final class App implements Logger {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Logger.header();
        // PT: vem do default de Logger
        // EN: from Logger default
        new App().log("PT: ola | EN: hi");

        // meuPlayground();
    }
}
