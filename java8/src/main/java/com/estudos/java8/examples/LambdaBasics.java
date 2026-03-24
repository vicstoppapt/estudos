package com.estudos.java8.examples;

import java.util.Arrays;
import java.util.List;

/**
 * PT: Lambdas. Teoria: README.md.
 * EN: Lambdas. Theory: README.md.
 */
public final class LambdaBasics {

    private LambdaBasics() {
    }

    // PT: Só um método abstrato → pode ser implementado por lambda
    // EN: Only one abstract method → can be implemented with a lambda
    @FunctionalInterface
    interface IntOp {
        int apply(int a, int b);
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Corpo da lambda vira implementação de IntOp.apply
        // EN: The lambda body becomes the implementation of IntOp.apply
        IntOp soma = (a, b) -> a + b;
        System.out.println(soma.apply(2, 3));

        // PT: forEach espera Consumer<? super T> — aqui println em maiúsculas
        // EN: forEach expects Consumer<? super T> — here println in uppercase
        List<String> nomes = Arrays.asList("ana", "bob");
        nomes.forEach(n -> System.out.println(n.toUpperCase()));

        // meuPlayground();
    }
}
