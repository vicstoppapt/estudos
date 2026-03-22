package com.estudos.frameworks.micronaut.api.dto;

import com.estudos.frameworks.micronaut.domain.Article;
import io.micronaut.serde.annotation.Serdeable;

import java.time.Instant;

@Serdeable
public record ArticleResponse(Long id, String title, String body, Instant createdAt) {

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(article.id(), article.title(), article.body(), article.createdAt());
    }
}
