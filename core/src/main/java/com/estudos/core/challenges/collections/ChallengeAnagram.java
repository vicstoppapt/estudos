package com.estudos.core.challenges.collections;

import java.util.Arrays;

/**
 * PT: Anagrama por ordenação de letras. Contexto: README em challenges/.
 * EN: Anagram by sorting letters. Context: README under challenges/.
 */
public final class ChallengeAnagram {

    private ChallengeAnagram() {
    }

    static boolean isAnagram(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        char[] ca = lettersLower(a);
        char[] cb = lettersLower(b);
        Arrays.sort(ca);
        Arrays.sort(cb);
        return Arrays.equals(ca, cb);
    }

    // PT: Só letras, em minúsculas — espaços e pontuação saem da contagem.
    // EN: Letters only, lowercased — spaces and punctuation are excluded from the count.
    private static char[] lettersLower(String s) {
        StringBuilder sb = new StringBuilder();
        for (int cp : s.codePoints().toArray()) {
            if (Character.isLetter(cp)) {
                sb.appendCodePoint(Character.toLowerCase(cp));
            }
        }
        return sb.toString().toCharArray();
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("listen", "silent"));
    }
}
