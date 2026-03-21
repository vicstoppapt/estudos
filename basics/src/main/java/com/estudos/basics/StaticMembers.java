package com.estudos.basics;

/**
 * Campos e métodos static. Teoria: staticmembers.md.
 */
public final class StaticMembers {

    private StaticMembers() {
    }

    // Um único contador por CLASSE (compartilhado por todas as instâncias desta classe interna)
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
        System.out.println("total static: " + Visitante.getTotalCriados());
        System.out.println("ultimo id: " + v3.getId());

        // meuPlayground();
    }
}
