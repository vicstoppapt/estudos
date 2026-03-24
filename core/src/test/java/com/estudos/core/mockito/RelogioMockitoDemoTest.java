package com.estudos.core.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * PT: Mockito: substitui dependência por objeto configurável (útil quando a real faria I/O ou tempo).
 * EN: Mockito: replaces a dependency with a configurable object (useful when the real one would do I/O or time).
 */
@ExtendWith(MockitoExtension.class)
class RelogioMockitoDemoTest {

    interface Relogio {
        long millis();
    }

    @Mock
    Relogio relogio;

    @Test
    void millisConfiguravel() {
        when(relogio.millis()).thenReturn(1_700_000_000_000L);
        assertThat(relogio.millis()).isEqualTo(1_700_000_000_000L);
        verify(relogio).millis();
    }
}
