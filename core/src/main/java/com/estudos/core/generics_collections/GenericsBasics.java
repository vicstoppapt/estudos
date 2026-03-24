package com.estudos.core.generics_collections;

import java.util.ArrayList;
import java.util.List;

/**
 * PT: List<?> e invariância. Teoria: README.md.
 * EN: List<?> and invariance. Theory: README.md.
 */
public final class GenericsBasics {

    private GenericsBasics() {
    }

    // PT: ? = “elementos de tipo desconhecido” — só tratamos como Object de forma segura.
    // EN: ? = “elements of unknown type” — we only treat them as Object in a type-safe way.
    static void imprimirLista(List<?> lista) {
        for (Object o : lista) {
            System.out.println(o);
        }
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        // PT: List<String> é subtipo de List<?> para passar como argumento — OK.
        // EN: List<String> is a subtype of List<?> for passing as an argument — OK.
        imprimirLista(strings);

        // PT: List<Object> objs = strings não compila — invariância.
        // EN: List<Object> objs = strings does not compile — invariance.
        // List<Object> objs = strings;

        // meuPlayground();
    }
}
