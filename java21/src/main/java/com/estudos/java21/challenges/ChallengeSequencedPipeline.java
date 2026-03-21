package com.estudos.java21.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

/** reversed() na prática. README em challenges/. */
public final class ChallengeSequencedPipeline {

    private ChallengeSequencedPipeline() {
    }

    static int somaReversa(SequencedCollection<Integer> nums) {
        int total = 0;
        // Iteração do último ao primeiro
        for (int n : nums.reversed()) {
            total += n;
        }
        return total;
    }

    public static void main(String[] args) {
        SequencedCollection<Integer> seq = new ArrayList<>(List.of(1, 2, 3));
        System.out.println("esperado 6 (3+2+1): " + somaReversa(seq));
    }
}
