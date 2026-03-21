package com.estudos.java8.examples;

import java.util.Optional;

/** Optional. Teoria: README.md. */
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
        // orElse só avalia o fallback quando empty (aqui não usa o literal "fallback" no primeiro caso)
        String a = buscar(true).orElse("fallback");
        String b = buscar(false).orElse("fallback");
        System.out.println(a + " / " + b);

        // Só executa o consumer se não for empty
        buscar(true).ifPresent(v -> System.out.println("presente: " + v));

        // meuPlayground();
    }
}
