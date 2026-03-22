package com.estudos.basics;

/**
 * Classe, instância, construtor, this. Teoria: objectsconstructorsthis.md.
 */
public final class ObjectsConstructorsThis {

    private ObjectsConstructorsThis() {
    }

    static final class Lampada {
        private final String id;
        private boolean ligada;

        // Construtor: roda ao dar new; inicializa estado do objeto
        Lampada(String id) {
            this.id = id; // this = "este objeto"; desambigua parâmetro vs campo
            this.ligada = false;
        }

        void ligar() {
            ligada = true;
        }

        String resumo() {
            return id + " ligada=" + ligada;
        }
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Lampada l = new Lampada("sala");
        l.ligar();
        System.out.println(l.resumo());

        // meuPlayground();
    }
}
