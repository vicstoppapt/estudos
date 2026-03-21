package com.estudos.core.challenges.collections;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ChallengeAnagramTest {

    @Test
    void anagramasIgnoramCaseEspacosPontuacao() {
        assertThat(ChallengeAnagram.isAnagram("Listen", "Silent")).isTrue();
        assertThat(ChallengeAnagram.isAnagram("Roma, ", "amor!")).isTrue();
        assertThat(ChallengeAnagram.isAnagram("abc", "def")).isFalse();
        assertThat(ChallengeAnagram.isAnagram(null, "a")).isFalse();
    }
}
