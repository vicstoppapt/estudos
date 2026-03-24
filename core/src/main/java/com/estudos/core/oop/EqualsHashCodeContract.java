package com.estudos.core.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * PT: equals + hashCode alinhados para HashMap. Teoria: README.md.
 * EN: equals + hashCode aligned for HashMap. Theory: README.md.
 */
public final class EqualsHashCodeContract {

    private EqualsHashCodeContract() {
    }

    static final class Pessoa {
        private final String nome;
        private final int id;

        Pessoa(String nome, int id) {
            this.nome = nome;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Pessoa)) {
                return false;
            }
            Pessoa pessoa = (Pessoa) o;
            return id == pessoa.id && Objects.equals(nome, pessoa.nome);
        }

        @Override
        public int hashCode() {
            // PT: Mesmos campos que equals — senão get( new Pessoa(...) ) falha.
            // EN: Same fields as equals — otherwise get( new Pessoa(...) ) fails.
            return Objects.hash(nome, id);
        }
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Map<Pessoa, String> map = new HashMap<>();
        Pessoa p = new Pessoa("Ana", 1);
        map.put(p, "dev");
        // PT: Mesma referência p — mesmo bucket e equals bate.
        // EN: Same reference p — same bucket and equals matches.
        System.out.println("get mesma chave: " + map.get(p));
        // PT: Outro objeto, mas equals+hashCode batem → mesmo bucket, equals encontra.
        // EN: Another object, but equals+hashCode match → same bucket, equals finds it.
        System.out.println("get chave equal: " + map.get(new Pessoa("Ana", 1)));

        // meuPlayground();
    }
}
