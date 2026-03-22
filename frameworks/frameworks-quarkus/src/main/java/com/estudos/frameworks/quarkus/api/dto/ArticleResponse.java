package com.estudos.frameworks.quarkus.api.dto;

import com.estudos.frameworks.quarkus.domain.Article;

import java.time.Instant;

public record ArticleResponse(Long id, String title, String body, Instant createdAt) {

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(article.id(), article.title(), article.body(), article.createdAt());
    }
}
