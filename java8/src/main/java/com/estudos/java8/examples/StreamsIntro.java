package com.estudos.java8.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Pipeline stream. Teoria: README.md. */
public final class StreamsIntro {

    private StreamsIntro() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        // filter/map são lazy; collect força avaliação e materializa lista
        List<Integer> paresDobro = nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println(paresDobro);

        // IntStream evita boxing na soma
        int soma = nums.stream().mapToInt(Integer::intValue).sum();
        System.out.println("soma: " + soma);

        // meuPlayground();
    }
}
