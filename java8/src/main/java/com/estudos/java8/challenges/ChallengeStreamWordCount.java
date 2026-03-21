package com.estudos.java8.challenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/** Contagem de palavras (stream). README em challenges/. */
public final class ChallengeStreamWordCount {

    private ChallengeStreamWordCount() {
    }

    static Map<String, Long> contar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return Collections.emptyMap();
        }
        // tokeniza por whitespace; agrupa palavra -> quantidade de ocorrências
        return Arrays.stream(texto.trim().split("\\s+"))
                .filter(w -> !w.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    public static void main(String[] args) {
        String texto = "ola java ola";
        System.out.println(contar(texto));
    }
}
