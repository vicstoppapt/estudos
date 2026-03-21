package com.estudos.java8.challenges;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ChallengeCollectorsGroupingTest {

    @Test
    void agrupaPorComprimento() {
        List<String> xs = Arrays.asList("a", "bb", "c", "dd");
        Map<Integer, List<String>> m = ChallengeCollectorsGrouping.agruparPorTamanho(xs);
        assertThat(m.get(1)).containsExactlyInAnyOrder("a", "c");
        assertThat(m.get(2)).containsExactlyInAnyOrder("bb", "dd");
    }
}
