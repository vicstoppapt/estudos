package com.estudos.basics;

/**
 * PT: Métodos: retorno, parâmetros, sobrecarga. Teoria: methodsandparameters.md.
 * EN: Methods: return values, parameters, overloading. Theory: methodsandparameters.md.
 */
public final class MethodsAndParameters {

    private MethodsAndParameters() {
    }

    // PT: void = não devolve valor ao chamador; só efeito (ex.: imprimir)
    // EN: void = returns no value to the caller; side effect only (e.g. printing)
    static void cumprimentar(String quem) {
        System.out.println("PT: ola " + quem);
        System.out.println("EN: hello " + quem);
    }

    static int somar(int a, int b) {
        return a + b;
    }

    // PT: Sobrecarga: mesmo nome, lista de parâmetros diferente
    // EN: Overloading: same name, different parameter list
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
