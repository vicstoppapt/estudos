package com.estudos.java11.examples;

import java.util.ArrayList;
import java.util.List;

/** var local. Teoria: README.md. */
public final class VarInference {

    private VarInference() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // Tipo inferido do lado direito: ArrayList<String>
        var lista = new ArrayList<String>();
        lista.add("x");
        for (var s : lista) {
            System.out.println(s);
        }
        // List.of é factory Java 9+ — lista imutável
        List<String> explicito = List.of("a", "b");
        System.out.println(explicito);

        // meuPlayground();
    }
}
