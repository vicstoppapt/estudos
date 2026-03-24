package com.estudos.java8.examples;

import java.util.Optional;

/**
 * PT: Optional. Teoria: README.md.
 * EN: Optional. Theory: README.md.
 */
public final class OptionalExample {

    private OptionalExample() {
    }

    static Optional<String> buscar(boolean existe) {
        return existe ? Optional.of("valor") : Optional.empty();
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: orElse só avalia o fallback quando empty (aqui não usa o literal "fallback" no primeiro caso)
        // EN: orElse evaluates the fallback only when empty (here the literal "fallback" is not used in the first case)
        String a = buscar(true).orElse("fallback");
        String b = buscar(false).orElse("fallback");
        System.out.println(a + " / " + b);

        // PT: Só executa o consumer se não for empty
        // EN: Runs the consumer only when non-empty
        buscar(true).ifPresent(v -> System.out.println("PT: presente: " + v + " | EN: present: " + v));

        // meuPlayground();
    }
}
