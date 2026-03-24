package com.estudos.core.challenges.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * PT: HashMap precisa de hashCode coerente com equals. Contexto: README em challenges/.
 * EN: HashMap requires hashCode consistent with equals. Context: README under challenges/.
 */
public final class ChallengeEqualsHashCode {

    private ChallengeEqualsHashCode() {
    }

    static final class Quebrado {
        private final String k;

        Quebrado(String k) {
            this.k = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Quebrado)) {
                return false;
            }
            Quebrado q = (Quebrado) o;
            return Objects.equals(k, q.k);
        }

        @Override
        public int hashCode() {
            // PT: Comente este método para ver map.get(busca) voltar null.
            // EN: Comment out this method to see map.get(busca) return null.
            return Objects.hash(k);
        }
    }

    public static void main(String[] args) {
        Map<Quebrado, String> map = new HashMap<>();
        map.put(new Quebrado("x"), "ok");
        Quebrado busca = new Quebrado("x");
        System.out.println("get: " + map.get(busca));
    }
}
