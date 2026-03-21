package com.estudos.java8.challenges;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ChallengeStreamWordCountTest {

    @Test
    void contagemPorPalavraCaseInsensitive() {
        assertThat(ChallengeStreamWordCount.contar("ola java ola"))
                .containsEntry("ola", 2L)
                .containsEntry("java", 1L);
    }

    @Test
    void textoVazio() {
        assertThat(ChallengeStreamWordCount.contar("   ")).isEmpty();
    }
}
