package com.estudos.core.oop;

import java.util.Objects;

/**
 * PT: toString / equals / hashCode. Teoria: README.md.
 * EN: toString / equals / hashCode. Theory: README.md.
 */
public final class ObjectMethods {

    private ObjectMethods() {
    }

    static final class Ponto {
        final int x;
        final int y;

        Ponto(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Ponto(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                // PT: Mesma referência.
                // EN: Same reference.
                return true;
            }
            if (!(o instanceof Ponto)) {
                // PT: Não é Ponto — não comparar campos.
                // EN: Not a Ponto — do not compare fields.
                return false;
            }
            Ponto ponto = (Ponto) o;
            return x == ponto.x && y == ponto.y;
        }

        @Override
        public int hashCode() {
            // PT: Deve usar os mesmos campos que equals (aqui x e y).
            // EN: Must use the same fields as equals (here x and y).
            return Objects.hash(x, y);
        }
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: toString sobrescrito → saída legível.
        // EN: Overridden toString → readable output.
        System.out.println(new Ponto(1, 2));
        // PT: Duas instâncias diferentes no heap, mesmo valor → equals true.
        // EN: Two distinct instances on the heap, same value → equals true.
        System.out.println(new Ponto(1, 2).equals(new Ponto(1, 2)));

        // meuPlayground();
    }
}
