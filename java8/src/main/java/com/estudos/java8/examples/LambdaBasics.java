package com.estudos.java8.examples;

import java.util.Arrays;
import java.util.List;

/** Lambdas. Teoria: README.md. */
public final class LambdaBasics {

    private LambdaBasics() {
    }

    // Só um método abstrato → pode ser implementado por lambda
    @FunctionalInterface
    interface IntOp {
        int apply(int a, int b);
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // Corpo da lambda vira implementação de IntOp.apply
        IntOp soma = (a, b) -> a + b;
        System.out.println(soma.apply(2, 3));

        // forEach espera Consumer<? super T> — aqui println em maiúsculas
        List<String> nomes = Arrays.asList("ana", "bob");
        nomes.forEach(n -> System.out.println(n.toUpperCase()));

        // meuPlayground();
    }
}
