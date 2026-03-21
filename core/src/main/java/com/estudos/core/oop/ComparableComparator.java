package com.estudos.core.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Ordem natural vs Comparator externo. Teoria: README.md.
 */
public final class ComparableComparator {

    private ComparableComparator() {
    }

    // compareTo define ordem "padrão" do tipo: aqui por preço crescente
    record Item(String nome, int preco) implements Comparable<Item> {
        @Override
        public int compareTo(Item o) {
            return Integer.compare(this.preco, o.preco);
        }
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        List<Item> itens = new ArrayList<>();
        itens.add(new Item("b", 20));
        itens.add(new Item("a", 10));
        // null = usar Comparable do elemento (Item.compareTo)
        itens.sort(null);
        System.out.println("por preco (Comparable): " + itens);

        // nova regra só para esta ordenação: extrator Item::nome
        itens.sort(Comparator.comparing(Item::nome));
        System.out.println("por nome (Comparator): " + itens);

        // meuPlayground();
    }
}
