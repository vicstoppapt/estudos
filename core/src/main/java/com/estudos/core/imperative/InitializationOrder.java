package com.estudos.core.imperative;

/**
 * Ordem de inicialização classe + instância. Teoria: README.md.
 */
public class InitializationOrder {

    // Roda quando a classe é carregada (antes de qualquer instância desta classe).
    static {
        System.out.println("1. bloco estatico InitializationOrder");
    }

    // Roda a cada new, imediatamente antes do construtor desta classe (após super-construtor).
    {
        System.out.println("4. bloco instancia InitializationOrder");
    }

    InitializationOrder() {
        System.out.println("5. construtor InitializationOrder");
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // 2) main roda com a classe já carregada (bloco estático 1 já executou)
        System.out.println("2. main comeca");
        // new Sub dispara cadeia: estático Sub (se ainda não rodou), depois construtor Sub → super primeiro
        new Sub();
        System.out.println("fim");

        // meuPlayground();
    }

    static class Sub extends InitializationOrder {
        static {
            System.out.println("3. bloco estatico Sub");
        }

        {
            System.out.println("6. bloco instancia Sub");
        }

        Sub() {
            System.out.println("7. construtor Sub");
        }
    }
}
