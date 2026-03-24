package com.estudos.basics;

import java.util.Arrays;

/**
 * PT: Arrays e null. Teoria: arraysandnull.md.
 * EN: Arrays and null. Theory: arraysandnull.md.
 */
public final class ArraysAndNull {

    private ArraysAndNull() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Array de primitivos: valores default 0 antes de atribuir
        // EN: Primitive array: default values 0 before assignment
        int[] nums = new int[3];
        nums[0] = 10;
        System.out.println(Arrays.toString(nums));

        // PT: Array de referências: default null em cada posição
        // EN: Reference array: default null in each slot
        String[] nomes = new String[2];
        nomes[0] = "a";
        System.out.println(Arrays.toString(nomes));

        // PT: null = referência a nenhum objeto; chamar método em null → NullPointerException
        // EN: null = reference to no object; calling a method on null → NullPointerException
        String s = null;
        if (s != null) {
            System.out.println(s.length());
        }

        // meuPlayground();
    }
}
