package com.estudos.java8.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PT: Pipeline stream. Teoria: README.md.
 * EN: Stream pipeline. Theory: README.md.
 */
public final class StreamsIntro {

    private StreamsIntro() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        // PT: filter/map são lazy; collect força avaliação e materializa lista
        // EN: filter/map are lazy; collect forces evaluation and materializes the list
        List<Integer> paresDobro = nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println(paresDobro);

        // PT: IntStream evita boxing na soma
        // EN: IntStream avoids boxing for the sum
        int soma = nums.stream().mapToInt(Integer::intValue).sum();
        System.out.println("PT: soma: " + soma + " | EN: sum: " + soma);

        // meuPlayground();
    }
}
