package com.estudos.core.generics_collections;

import java.util.ArrayList;
import java.util.List;

/**
 * List<?> e invariância. Teoria: README.md.
 */
public final class GenericsBasics {

    private GenericsBasics() {
    }

    // ? = “elementos de tipo desconhecido” — só tratamos como Object de forma segura
    static void imprimirLista(List<?> lista) {
        for (Object o : lista) {
            System.out.println(o);
        }
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        // List<String> é subtipo de List<?> para passar como argumento — OK
        imprimirLista(strings);

        // List<Object> objs = strings; // nao compila — invariância

        // meuPlayground();
    }
}
