package com.estudos.java8.examples;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** java.util.function. Teoria: README.md. */
public final class FunctionalInterfaces {

    private FunctionalInterfaces() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Predicate<String> naoVazio = s -> s != null && !s.isEmpty();
        Function<String, Integer> tamanho = String::length; // equivale a s -> s.length()
        Consumer<String> print = System.out::println;
        Supplier<Double> random = Math::random; // sem argumentos, retorna Double

        print.accept("naoVazio('a'): " + naoVazio.test("a"));
        print.accept("tamanho abc: " + tamanho.apply("abc"));
        print.accept("random: " + random.get());

        // meuPlayground();
    }
}
