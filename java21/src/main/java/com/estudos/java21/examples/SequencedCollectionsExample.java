package com.estudos.java21.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

/** SequencedCollection. Teoria: README.md. */
public final class SequencedCollectionsExample {

    private SequencedCollectionsExample() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        SequencedCollection<String> seq = new ArrayList<>(List.of("a", "b", "c"));
        System.out.println("first: " + seq.getFirst());
        System.out.println("last: " + seq.getLast());
        // reversed() é vista — alterações na coleção base refletem (e vice-versa conforme API)
        System.out.println("reversed: " + seq.reversed());

        // meuPlayground();
    }
}
