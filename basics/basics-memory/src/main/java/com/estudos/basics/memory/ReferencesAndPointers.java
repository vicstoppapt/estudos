package com.estudos.basics.memory;

import java.util.Arrays;

/**
 * PT: Referências (estilo “ponteiro seguro”): identidade de objeto, partilha de referência, {@code null}.
 * Teoria longa: {@code pointersreferencesdeepdive.md} no mesmo pacote.
 * EN: References (“safe pointer” style): object identity, shared reference, {@code null}.
 * Longer theory: {@code pointersreferencesdeepdive.md} in the same package.
 */
public final class ReferencesAndPointers {

    private ReferencesAndPointers() {
    }

    static void duasVariaveisMesmoObjeto() {
        int[] a = {1, 2, 3};
        int[] b = a;
        b[0] = 99;
        System.out.println("PT: a[0]=" + a[0] + " (b aponta para o mesmo array que a)");
        System.out.println("EN: a[0]=" + a[0] + " (b points to the same array as a)");
    }

    static void copiaReferenciaParametro(int[] arr) {
        if (arr == null) {
            return;
        }
        arr[0] = -1;
    }

    static void tentarTrocarReferenciaParametro(int[] arr) {
        arr = new int[]{7, 8, 9};
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        String x = new String("olá");
        String y = new String("olá");
        System.out.println("x == y ? " + (x == y));
        System.out.println("x.equals(y) ? " + x.equals(y));
        System.out.println("identityHashCode x vs y: " + System.identityHashCode(x) + " vs " + System.identityHashCode(y));

        String z = x;
        System.out.println("PT: x == z ? " + (x == z) + " (mesma referência)");
        System.out.println("EN: x == z ? " + (x == z) + " (same reference)");

        duasVariaveisMesmoObjeto();

        int[] dados = {10, 20};
        copiaReferenciaParametro(dados);
        System.out.println("PT: após método que altera elemento: " + Arrays.toString(dados));
        System.out.println("EN: after method that mutates element: " + Arrays.toString(dados));

        tentarTrocarReferenciaParametro(dados);
        System.out.println("PT: após método que só reatribui parâmetro local: " + Arrays.toString(dados));
        System.out.println("EN: after method that only reassigns local parameter: " + Arrays.toString(dados));

        int[] nulo = null;
        System.out.println("PT: nulo == null ? " + (nulo == null));
        System.out.println("EN: null ref == null ? " + (nulo == null));

        // meuPlayground();
    }
}
