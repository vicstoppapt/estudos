package com.estudos.core.generics_collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * PT: Amostra de List / Set / Map / Queue. Teoria: README.md.
 * EN: Sample of List / Set / Map / Queue. Theory: README.md.
 */
public final class CollectionsOverview {

    private CollectionsOverview() {
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Lista: ordem de inserção, índice, duplicatas permitidas.
        // EN: List: insertion order, index, duplicates allowed.
        List<String> list = new ArrayList<>();
        list.add("b");
        list.add("a");
        System.out.println("ArrayList: " + list);

        // PT: Conjunto: segundo add("x") não aumenta tamanho.
        // EN: Set: a second add("x") does not increase size.
        Set<String> set = new HashSet<>();
        set.add("x");
        set.add("x");
        System.out.println("HashSet tamanho: " + set.size());

        // PT: Mapa hash: média O(1) get/put.
        // EN: Hash map: average O(1) get/put.
        Map<String, Integer> hash = new HashMap<>();
        hash.put("k", 1);
        System.out.println("HashMap: " + hash);

        // PT: Mantém ordem de inserção nas iterações.
        // EN: Preserves insertion order when iterating.
        Map<String, Integer> linked = new LinkedHashMap<>();
        linked.put("z", 3);
        linked.put("y", 2);
        System.out.println("LinkedHashMap itera na ordem de inserção: " + linked);

        // PT: Chaves ordenadas (Comparable das chaves ou Comparator no construtor).
        // EN: Keys sorted (key Comparable or Comparator in constructor).
        Map<String, Integer> tree = new TreeMap<>();
        tree.put("c", 1);
        tree.put("a", 2);
        System.out.println("TreeMap chaves ordenadas: " + tree.keySet());

        // PT: Fila como deque: poll() remove e devolve o primeiro.
        // EN: Queue as deque: poll() removes and returns the first element.
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        q.add(2);
        System.out.println("Deque poll: " + q.poll());

        // meuPlayground();
    }
}
