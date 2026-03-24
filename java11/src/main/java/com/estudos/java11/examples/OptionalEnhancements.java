package com.estudos.java11.examples;

import java.util.Optional;

/**
 * PT: orElseThrow sem args. Teoria: README.md.
 * EN: orElseThrow with no args. Theory: README.md.
 */
public final class OptionalEnhancements {

    private OptionalEnhancements() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Optional<String> vazio = Optional.empty();
        try {
            // PT: NoSuchElementException
            // EN: NoSuchElementException
            vazio.orElseThrow();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }
        System.out.println(Optional.of("ok").orElseThrow());

        // meuPlayground();
    }
}
