package com.estudos.java17.challenges;

/**
 * PT: Sealed + instanceof. README em challenges/.
 * EN: Sealed types + instanceof. README in challenges/.
 */
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
        // PT: permits fecha o conjunto — compile-time sabe que só existem estes dois
        // EN: permits close the set — at compile time only these two exist
        throw new IllegalStateException("PT: tipo selado desconhecido | EN: unknown sealed subtype");
    }

    public static void main(String[] args) {
        String c1 = cor(new Quadrado(1));
        System.out.println("PT: " + c1 + " | EN: " + ("azul".equals(c1) ? "blue" : "red"));
        String c2 = cor(new Circulo(2));
        System.out.println("PT: " + c2 + " | EN: " + ("azul".equals(c2) ? "blue" : "red"));
    }
}
