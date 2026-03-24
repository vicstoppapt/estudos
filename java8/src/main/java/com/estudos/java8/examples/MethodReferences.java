package com.estudos.java8.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PT: Method references. Teoria: README.md.
 * EN: Method references. Theory: README.md.
 */
public final class MethodReferences {

    private MethodReferences() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        List<String> xs = Arrays.asList("a", "b", "c");
        // PT: String::toUpperCase = instância em cada elemento do stream
        // EN: String::toUpperCase = bound instance on each stream element
        xs.stream().map(String::toUpperCase).forEach(System.out::println);

        // PT: StringBuilder::new aplicado a cada String do stream (construtor que aceita String)
        // EN: StringBuilder::new applied to each stream String (constructor taking String)
        List<StringBuilder> builders = xs.stream().map(StringBuilder::new).collect(Collectors.toList());
        System.out.println(builders);

        // meuPlayground();
    }
}
