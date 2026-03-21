package com.estudos.java8.examples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PredicateMockitoDemoTest {

    @Mock
    Predicate<String> filtro;

    @Test
    void streamUsaPredicateMockado() {
        when(filtro.test(anyString())).thenReturn(true);
        List<String> out = java.util.Arrays.asList("a", "b").stream()
                .filter(filtro::test)
                .collect(Collectors.toList());
        assertThat(out).containsExactly("a", "b");
    }
}
