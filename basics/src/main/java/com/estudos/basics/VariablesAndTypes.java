package com.estudos.basics;

/**
 * Primitivos vs referência (String, Integer). Teoria longa: variablesandtypes.md.
 */
public final class VariablesAndTypes {

    private VariablesAndTypes() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // --- Primitivo: a variável "é" o valor (bits do número no frame do método) ---
        int quantidade = 3;
        double preco = 9.99;
        boolean ativo = true;
        char letra = 'A';

        // --- Referência: a variável guarda ponteiro para objeto String no heap ---
        String nome = "ana";

        final int limite = 10;

        System.out.println(quantidade + " " + preco + " " + ativo + " " + letra + " " + nome + " " + limite);

        // --- int vs Integer: mesmo "número 3", papéis diferentes para a JVM ---
        int a = 3;
        int b = 3;
        System.out.println("primitivo a == b: " + (a == b)); // compara VALORES

        Integer boxed1 = 3; // autoboxing → Integer.valueOf(3)
        Integer boxed2 = 3;
        System.out.println("Integer referencial boxed1 == boxed2: " + (boxed1 == boxed2));
        // frequentemente true para -128..127 (cache de Integer); não dependas disso para lógica
        System.out.println("Integer equals: " + boxed1.equals(boxed2)); // compara conteúdo do objeto

        Integer x = Integer.valueOf(200);
        Integer y = Integer.valueOf(200);
        System.out.println("200 cache == ? " + (x == y)); // muitas vezes false → usar equals para valor
        System.out.println("200 equals: " + x.equals(y));

        // --- null só em referência ---
        String opcional = null;
        if (opcional == null) {
            System.out.println("referencia null: nenhum objeto String");
        }

        // int nulo; // não existe — por isso existem wrappers quando precisas de "ausente"

        // meuPlayground();
    }
}
