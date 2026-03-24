package com.estudos.core.declarative;

import java.util.Arrays;
import java.util.List;

/**
 * PT: Soma dos pares: for vs stream. Teoria: README.md.
 * EN: Sum of evens: for vs stream. Theory: README.md.
 */
public final class DeclarativeVsImperative {

    private DeclarativeVsImperative() {
    }

    static int somaParesImperativo(List<Integer> nums) {
        int total = 0;
        for (Integer n : nums) {
            if (n != null && n % 2 == 0) {
                // PT: Acumula só pares não-nulos.
                // EN: Accumulates only non-null evens.
                total += n;
            }
        }
        return total;
    }

    static int somaParesDeclarativo(List<Integer> nums) {
        return nums.stream()
                .filter(n -> n != null && n % 2 == 0)
                // PT: IntStream a partir dos Integer.
                // EN: IntStream from the Integer values.
                .mapToInt(Integer::intValue)
                // PT: Operação terminal.
                // EN: Terminal operation.
                .sum();
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Contém null de propósito — ambos os métodos devem ignorar.
        // EN: Contains null on purpose — both methods must ignore it.
        List<Integer> xs = Arrays.asList(1, 2, 3, 4, null, 6);
        System.out.println("imperativo: " + somaParesImperativo(xs));
        System.out.println("declarativo: " + somaParesDeclarativo(xs));

        // meuPlayground();
    }
}
