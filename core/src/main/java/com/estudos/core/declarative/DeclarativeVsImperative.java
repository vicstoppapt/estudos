package com.estudos.core.declarative;

import java.util.Arrays;
import java.util.List;

/**
 * Soma dos pares: for vs stream. Teoria: README.md.
 */
public final class DeclarativeVsImperative {

    private DeclarativeVsImperative() {
    }

    static int somaParesImperativo(List<Integer> nums) {
        int total = 0;
        for (Integer n : nums) {
            if (n != null && n % 2 == 0) {
                total += n; // acumula só pares não-nulos
            }
        }
        return total;
    }

    static int somaParesDeclarativo(List<Integer> nums) {
        return nums.stream()
                .filter(n -> n != null && n % 2 == 0)
                .mapToInt(Integer::intValue) // IntStream a partir dos Integer
                .sum(); // operação terminal
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // contém null de propósito — ambos os métodos devem ignorar
        List<Integer> xs = Arrays.asList(1, 2, 3, 4, null, 6);
        System.out.println("imperativo: " + somaParesImperativo(xs));
        System.out.println("declarativo: " + somaParesDeclarativo(xs));

        // meuPlayground();
    }
}
