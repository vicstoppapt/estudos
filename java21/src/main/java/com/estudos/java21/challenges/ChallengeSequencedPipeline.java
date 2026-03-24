package com.estudos.java21.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

/**
 * PT: reversed() na prática. README em challenges/.
 * EN: reversed() in practice. README in challenges/.
 */
public final class ChallengeSequencedPipeline {

    private ChallengeSequencedPipeline() {
    }

    static int somaReversa(SequencedCollection<Integer> nums) {
        int total = 0;
        // PT: Iteração do último ao primeiro
        // EN: Iteration from last to first
        for (int n : nums.reversed()) {
            total += n;
        }
        return total;
    }

    public static void main(String[] args) {
        SequencedCollection<Integer> seq = new ArrayList<>(List.of(1, 2, 3));
        System.out.println("PT: esperado 6 (3+2+1): " + somaReversa(seq) + " | EN: expected 6 (3+2+1): "
                + somaReversa(seq));
    }
}
