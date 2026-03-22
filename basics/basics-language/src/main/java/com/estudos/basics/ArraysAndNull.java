package com.estudos.basics;

import java.util.Arrays;

/**
 * Arrays e null. Teoria: arraysandnull.md.
 */
public final class ArraysAndNull {

    private ArraysAndNull() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // Array de primitivos: valores default 0 antes de atribuir
        int[] nums = new int[3];
        nums[0] = 10;
        System.out.println(Arrays.toString(nums));

        // Array de referências: default null em cada posição
        String[] nomes = new String[2];
        nomes[0] = "a";
        System.out.println(Arrays.toString(nomes));

        // null = referência a nenhum objeto; chamar método em null → NullPointerException
        String s = null;
        if (s != null) {
            System.out.println(s.length());
        }

        // meuPlayground();
    }
}
