package com.estudos.basics;

/**
 * PT: Classe, instância, construtor, this. Teoria: objectsconstructorsthis.md.
 * EN: Class, instance, constructor, this. Theory: objectsconstructorsthis.md.
 */
public final class ObjectsConstructorsThis {

    private ObjectsConstructorsThis() {
    }

    static final class Lampada {
        private final String id;
        private boolean ligada;

        // PT: Construtor: roda ao dar new; inicializa estado do objeto
        // EN: Constructor: runs on new; initializes object state
        Lampada(String id) {
            // PT: this = "este objeto"; desambigua parâmetro vs campo
            // EN: this = "this object"; disambiguates parameter vs field
            this.id = id;
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
        System.out.println("PT: " + l.resumo());
        System.out.println("EN: " + l.resumo().replace(" ligada=", " on="));

        // meuPlayground();
    }
}
