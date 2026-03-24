package com.estudos.java8.examples;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * PT: java.util.function. Teoria: README.md.
 * EN: java.util.function. Theory: README.md.
 */
public final class FunctionalInterfaces {

    private FunctionalInterfaces() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Predicate<String> naoVazio = s -> s != null && !s.isEmpty();
        // PT: equivale a s -> s.length()
        // EN: same as s -> s.length()
        Function<String, Integer> tamanho = String::length;
        Consumer<String> print = System.out::println;
        // PT: sem argumentos, retorna Double
        // EN: no arguments, returns Double
        Supplier<Double> random = Math::random;

        print.accept("PT: naoVazio('a'): " + naoVazio.test("a") + " | EN: nonEmpty('a'): " + naoVazio.test("a"));
        print.accept("PT: tamanho abc: " + tamanho.apply("abc") + " | EN: length abc: " + tamanho.apply("abc"));
        print.accept("PT: random: " + random.get() + " | EN: random: " + random.get());

        // meuPlayground();
    }
}
