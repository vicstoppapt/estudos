package com.estudos.java21.challenges;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

import org.junit.jupiter.api.Test;

class ChallengeSequencedPipelineTest {

    @Test
    void somaOrdemReversa() {
        SequencedCollection<Integer> seq = new ArrayList<>(List.of(1, 2, 3, 10));
        assertThat(ChallengeSequencedPipeline.somaReversa(seq)).isEqualTo(10 + 3 + 2 + 1);
    }
}
