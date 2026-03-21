package com.estudos.java17.challenges;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ChallengeSealedSwitchTest {

    @Test
    void coresPorFormato() {
        assertThat(ChallengeSealedSwitch.cor(new ChallengeSealedSwitch.Quadrado(1))).isEqualTo("azul");
        assertThat(ChallengeSealedSwitch.cor(new ChallengeSealedSwitch.Circulo(2))).isEqualTo("vermelho");
    }
}
