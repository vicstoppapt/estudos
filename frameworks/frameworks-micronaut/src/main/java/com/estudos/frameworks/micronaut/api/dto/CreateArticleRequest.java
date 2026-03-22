package com.estudos.frameworks.micronaut.api.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Serdeable
@Introspected
public record CreateArticleRequest(
        @NotBlank(message = "titulo obrigatorio")
        @Size(max = 200, message = "titulo ate 200 caracteres")
        String title,
        @Size(max = 10_000)
        String body
) {
}
