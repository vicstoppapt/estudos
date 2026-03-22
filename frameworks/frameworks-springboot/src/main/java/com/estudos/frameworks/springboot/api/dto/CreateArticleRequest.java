package com.estudos.frameworks.springboot.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada com Bean Validation.
 */
public record CreateArticleRequest(
        @NotBlank(message = "titulo obrigatorio")
        @Size(max = 200, message = "titulo ate 200 caracteres")
        String title,
        @Size(max = 10_000)
        String body
) {
}
