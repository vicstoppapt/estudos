package com.estudos.java11.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * PT: var local. Teoria: README.md.
 * EN: Local var. Theory: README.md.
 */
public final class VarInference {

    private VarInference() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Tipo inferido do lado direito: ArrayList<String>
        // EN: Type inferred from the right-hand side: ArrayList<String>
        var lista = new ArrayList<String>();
        lista.add("x");
        for (var s : lista) {
            System.out.println(s);
        }
        // PT: List.of é factory Java 9+ — lista imutável
        // EN: List.of is a Java 9+ factory — immutable list
        List<String> explicito = List.of("a", "b");
        System.out.println(explicito);

        // meuPlayground();
    }
}
