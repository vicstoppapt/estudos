package com.estudos.core.challenges.oop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ChallengeEqualsHashCodeTest {

    @Test
    void mapEncontraChaveLogicamenteIgual() {
        Map<ChallengeEqualsHashCode.Quebrado, String> map = new HashMap<>();
        map.put(new ChallengeEqualsHashCode.Quebrado("x"), "ok");
        assertThat(map.get(new ChallengeEqualsHashCode.Quebrado("x"))).isEqualTo("ok");
    }
}
