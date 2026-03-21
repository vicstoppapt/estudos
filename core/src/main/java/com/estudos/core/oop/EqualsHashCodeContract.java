package com.estudos.core.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * equals + hashCode alinhados para HashMap. Teoria: README.md.
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
            // Mesmos campos que equals — senão get( new Pessoa(...) ) falha
            return Objects.hash(nome, id);
        }
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Map<Pessoa, String> map = new HashMap<>();
        Pessoa p = new Pessoa("Ana", 1);
        map.put(p, "dev");
        // mesma referência p — mesmo bucket e equals bate
        System.out.println("get mesma chave: " + map.get(p));
        // outro objeto, mas equals+hashCode batem → mesmo bucket, equals encontra
        System.out.println("get chave equal: " + map.get(new Pessoa("Ana", 1)));

        // meuPlayground();
    }
}
