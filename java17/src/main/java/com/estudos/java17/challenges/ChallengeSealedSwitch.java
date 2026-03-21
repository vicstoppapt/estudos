package com.estudos.java17.challenges;

/** Sealed + instanceof. README em challenges/. */
public final class ChallengeSealedSwitch {

    private ChallengeSealedSwitch() {
    }

    sealed interface Formato permits Quadrado, Circulo {
    }

    record Quadrado(double lado) implements Formato {
    }

    record Circulo(double raio) implements Formato {
    }

    static String cor(Formato f) {
        if (f instanceof Quadrado) {
            return "azul";
        }
        if (f instanceof Circulo) {
            return "vermelho";
        }
        // permits fecha o conjunto — compile-time sabe que só existem estes dois
        throw new IllegalStateException("tipo selado desconhecido");
    }

    public static void main(String[] args) {
        System.out.println(cor(new Quadrado(1)));
        System.out.println(cor(new Circulo(2)));
    }
}
