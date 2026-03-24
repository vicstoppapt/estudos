package com.estudos.basics;

/**
 * PT: Primitivos vs referência (String, Integer). Teoria longa: variablesandtypes.md.
 * EN: Primitives vs reference (String, Integer). Longer theory: variablesandtypes.md.
 */
public final class VariablesAndTypes {

    private VariablesAndTypes() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Primitivo: a variável "é" o valor (bits do número no frame do método)
        // EN: Primitive: the variable "is" the value (number bits in the method frame)
        int quantidade = 3;
        double preco = 9.99;
        boolean ativo = true;
        char letra = 'A';

        // PT: Referência: a variável guarda ponteiro para objeto String no heap
        // EN: Reference: the variable holds a pointer to a String object on the heap
        String nome = "ana";

        final int limite = 10;

        System.out.println(quantidade + " " + preco + " " + ativo + " " + letra + " " + nome + " " + limite);

        // PT: int vs Integer: mesmo "número 3", papéis diferentes para a JVM
        // EN: int vs Integer: same numeric 3, different roles for the JVM
        int a = 3;
        int b = 3;
        // PT: compara VALORES
        // EN: compares VALUES
        System.out.println("PT: primitivo a == b: " + (a == b));
        System.out.println("EN: primitive a == b: " + (a == b));

        // PT: autoboxing → Integer.valueOf(3)
        // EN: autoboxing → Integer.valueOf(3)
        Integer boxed1 = 3;
        Integer boxed2 = 3;
        System.out.println("PT: Integer referencial boxed1 == boxed2: " + (boxed1 == boxed2));
        System.out.println("EN: Integer reference boxed1 == boxed2: " + (boxed1 == boxed2));
        // PT: frequentemente true para -128..127 (cache de Integer); não dependas disso para lógica
        // EN: often true for -128..127 (Integer cache); do not rely on this for program logic
        // PT: compara conteúdo do objeto
        // EN: compares object contents
        System.out.println("PT: Integer equals: " + boxed1.equals(boxed2));
        System.out.println("EN: Integer equals: " + boxed1.equals(boxed2));

        Integer x = Integer.valueOf(200);
        Integer y = Integer.valueOf(200);
        // PT: muitas vezes false → usar equals para valor
        // EN: often false → use equals for value comparison
        System.out.println("PT: 200 cache == ? " + (x == y));
        System.out.println("EN: 200 cache == ? " + (x == y));
        System.out.println("PT: 200 equals: " + x.equals(y));
        System.out.println("EN: 200 equals: " + x.equals(y));

        // PT: null só em referência
        // EN: null only on reference types
        String opcional = null;
        if (opcional == null) {
            System.out.println("PT: referencia null: nenhum objeto String");
            System.out.println("EN: null reference: no String object");
        }

        // PT: int nulo; não existe — por isso existem wrappers quando precisas de "ausente"
        // EN: int null; does not exist — hence wrappers when you need "absent"
        // int nulo;

        // meuPlayground();
    }
}
