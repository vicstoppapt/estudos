package com.estudos.java17.challenges;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ChallengeRecordValidationTest {

    @Test
    void emailValido() {
        assertThat(new ChallengeRecordValidation.Email("a@b").valor()).isEqualTo("a@b");
    }

    @Test
    void rejeitaInvalidos() {
        assertThatThrownBy(() -> new ChallengeRecordValidation.Email(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new ChallengeRecordValidation.Email("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new ChallengeRecordValidation.Email("semarroba")).isInstanceOf(IllegalArgumentException.class);
    }
}
