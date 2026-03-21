package com.estudos.java8.challenges;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** groupingBy por tamanho. README em challenges/. */
public final class ChallengeCollectorsGrouping {

    private ChallengeCollectorsGrouping() {
    }

    static Map<Integer, List<String>> agruparPorTamanho(List<String> palavras) {
        // Cada chave = length; valor = lista de strings com esse length
        return palavras.stream().collect(Collectors.groupingBy(String::length));
    }

    public static void main(String[] args) {
        List<String> xs = Arrays.asList("a", "bb", "c", "dd");
        System.out.println(agruparPorTamanho(xs));
    }
}
