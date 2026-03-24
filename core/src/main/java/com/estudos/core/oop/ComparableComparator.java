package com.estudos.core.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * PT: Ordem natural vs Comparator externo. Teoria: README.md.
 * EN: Natural order vs external Comparator. Theory: README.md.
 */
public final class ComparableComparator {

    private ComparableComparator() {
    }

    // PT: compareTo define ordem "padrão" do tipo: aqui por preço crescente.
    // EN: compareTo defines the type's default order: here by ascending price.
    record Item(String nome, int preco) implements Comparable<Item> {
        @Override
        public int compareTo(Item o) {
            return Integer.compare(this.preco, o.preco);
        }
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        List<Item> itens = new ArrayList<>();
        itens.add(new Item("b", 20));
        itens.add(new Item("a", 10));
        // PT: null = usar Comparable do elemento (Item.compareTo).
        // EN: null = use the element's Comparable (Item.compareTo).
        itens.sort(null);
        System.out.println("por preco (Comparable): " + itens);

        // PT: Nova regra só para esta ordenação: extrator Item::nome.
        // EN: New rule only for this sort: extractor Item::nome.
        itens.sort(Comparator.comparing(Item::nome));
        System.out.println("por nome (Comparator): " + itens);

        // meuPlayground();
    }
}
