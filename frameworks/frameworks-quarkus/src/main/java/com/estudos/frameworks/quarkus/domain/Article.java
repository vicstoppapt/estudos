package com.estudos.frameworks.quarkus.domain;

import java.time.Instant;

public record Article(Long id, String title, String body, Instant createdAt) {
}
