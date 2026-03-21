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
 * Amostra de List / Set / Map / Queue. Teoria: README.md.
 */
public final class CollectionsOverview {

    private CollectionsOverview() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // Lista: ordem de inserção, índice, duplicatas permitidas
        List<String> list = new ArrayList<>();
        list.add("b");
        list.add("a");
        System.out.println("ArrayList: " + list);

        // Conjunto: segundo add("x") não aumenta tamanho
        Set<String> set = new HashSet<>();
        set.add("x");
        set.add("x");
        System.out.println("HashSet tamanho: " + set.size());

        // Mapa hash: média O(1) get/put
        Map<String, Integer> hash = new HashMap<>();
        hash.put("k", 1);
        System.out.println("HashMap: " + hash);

        // Mantém ordem de inserção nas iterações
        Map<String, Integer> linked = new LinkedHashMap<>();
        linked.put("z", 3);
        linked.put("y", 2);
        System.out.println("LinkedHashMap itera na ordem de inserção: " + linked);

        // Chaves ordenadas (Comparable das chaves ou Comparator no construtor)
        Map<String, Integer> tree = new TreeMap<>();
        tree.put("c", 1);
        tree.put("a", 2);
        System.out.println("TreeMap chaves ordenadas: " + tree.keySet());

        // Fila como deque: poll() remove e devolve o primeiro
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        q.add(2);
        System.out.println("Deque poll: " + q.poll());

        // meuPlayground();
    }
}
