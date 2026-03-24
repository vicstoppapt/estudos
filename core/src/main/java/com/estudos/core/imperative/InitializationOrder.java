package com.estudos.core.imperative;

/**
 * PT: Ordem de inicialização classe + instância. Teoria: README.md.
 * EN: Class + instance initialization order. Theory: README.md.
 */
public class InitializationOrder {

    // PT: Roda quando a classe é carregada (antes de qualquer instância desta classe).
    // EN: Runs when the class is loaded (before any instance of this class).
    static {
        System.out.println("1. bloco estatico InitializationOrder");
    }

    // PT: Roda a cada new, imediatamente antes do construtor desta classe (após super-construtor).
    // EN: Runs on each new, immediately before this class's constructor (after super-constructor).
    {
        System.out.println("4. bloco instancia InitializationOrder");
    }

    InitializationOrder() {
        System.out.println("5. construtor InitializationOrder");
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: 2) main roda com a classe já carregada (bloco estático 1 já executou).
        // EN: 2) main runs with the class already loaded (static block 1 already ran).
        System.out.println("2. main comeca");
        // PT: new Sub dispara cadeia: estático Sub (se ainda não rodou), depois construtor Sub → super primeiro.
        // EN: new Sub triggers chain: Sub static (if not yet run), then Sub constructor → super first.
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
