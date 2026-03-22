package com.estudos.basics;

/**
 * Métodos: retorno, parâmetros, sobrecarga. Teoria: methodsandparameters.md.
 */
public final class MethodsAndParameters {

    private MethodsAndParameters() {
    }

    // void = não devolve valor ao chamador; só efeito (ex.: imprimir)
    static void cumprimentar(String quem) {
        System.out.println("ola " + quem);
    }

    static int somar(int a, int b) {
        return a + b;
    }

    // Sobrecarga: mesmo nome, lista de parâmetros diferente
    static int somar(int a, int b, int c) {
        return a + b + c;
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        cumprimentar("mundo");
        System.out.println(somar(2, 3));
        System.out.println(somar(1, 2, 3));

        // meuPlayground();
    }
}
