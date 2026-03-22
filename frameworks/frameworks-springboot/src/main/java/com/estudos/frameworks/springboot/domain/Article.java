package com.estudos.frameworks.springboot.domain;

import java.time.Instant;

/**
 * Modelo de domínio: artigo persistível (imutável).
 */
public record Article(Long id, String title, String body, Instant createdAt) {
}
