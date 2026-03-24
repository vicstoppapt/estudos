package com.estudos.java8.challenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PT: Contagem de palavras (stream). README em challenges/.
 * EN: Word count (stream). README in challenges/.
 */
public final class ChallengeStreamWordCount {

    private ChallengeStreamWordCount() {
    }

    static Map<String, Long> contar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return Collections.emptyMap();
        }
        // PT: tokeniza por whitespace; agrupa palavra -> quantidade de ocorrências
        // EN: tokenize on whitespace; group word -> occurrence count
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
