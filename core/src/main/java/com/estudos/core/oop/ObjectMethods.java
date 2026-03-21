package com.estudos.core.oop;

import java.util.Objects;

/**
 * toString / equals / hashCode. Teoria: README.md.
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
                return true; // mesma referência
            }
            if (!(o instanceof Ponto)) {
                return false; // não é Ponto — não comparar campos
            }
            Ponto ponto = (Ponto) o;
            return x == ponto.x && y == ponto.y;
        }

        @Override
        public int hashCode() {
            // Deve usar os mesmos campos que equals (aqui x e y)
            return Objects.hash(x, y);
        }
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // toString sobrescrito → saída legível
        System.out.println(new Ponto(1, 2));
        // duas instâncias diferentes no heap, mesmo valor → equals true
        System.out.println(new Ponto(1, 2).equals(new Ponto(1, 2)));

        // meuPlayground();
    }
}
