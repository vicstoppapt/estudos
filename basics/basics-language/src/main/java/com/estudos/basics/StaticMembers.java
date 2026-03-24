package com.estudos.basics;

/**
 * PT: Campos e métodos static. Teoria: staticmembers.md.
 * EN: Static fields and methods. Theory: staticmembers.md.
 */
public final class StaticMembers {

    private StaticMembers() {
    }

    // PT: Um único contador por CLASSE (compartilhado por todas as instâncias desta classe interna)
    // EN: A single counter per CLASS (shared by all instances of this nested class)
    static final class Visitante {
        private static int totalCriados;
        private final int id;

        Visitante() {
            totalCriados++;
            id = totalCriados;
        }

        static int getTotalCriados() {
            return totalCriados;
        }

        int getId() {
            return id;
        }
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        new Visitante();
        new Visitante();
        Visitante v3 = new Visitante();
        System.out.println("PT: total static: " + Visitante.getTotalCriados());
        System.out.println("EN: static total: " + Visitante.getTotalCriados());
        System.out.println("PT: ultimo id: " + v3.getId());
        System.out.println("EN: last id: " + v3.getId());

        // meuPlayground();
    }
}
