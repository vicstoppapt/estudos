package com.estudos.java11.examples;

import java.util.Optional;

/** orElseThrow sem args. Teoria: README.md. */
public final class OptionalEnhancements {

    private OptionalEnhancements() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Optional<String> vazio = Optional.empty();
        try {
            vazio.orElseThrow(); // NoSuchElementException
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }
        System.out.println(Optional.of("ok").orElseThrow());

        // meuPlayground();
    }
}
