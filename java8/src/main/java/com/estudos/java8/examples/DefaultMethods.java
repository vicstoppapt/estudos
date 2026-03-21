package com.estudos.java8.examples;

/** default/static em interface. Teoria: README.md. */
public final class DefaultMethods {

    private DefaultMethods() {
    }

    interface Logger {
        // Implementação padrão — quem implementa a interface herda sem precisar reescrever
        default void log(String msg) {
            System.out.println("[log] " + msg);
        }

        // Não passa por instância; chamada Logger.header()
        static void header() {
            System.out.println("=== inicio ===");
        }
    }

    static final class App implements Logger {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Logger.header();
        new App().log("ola"); // vem do default de Logger

        // meuPlayground();
    }
}
