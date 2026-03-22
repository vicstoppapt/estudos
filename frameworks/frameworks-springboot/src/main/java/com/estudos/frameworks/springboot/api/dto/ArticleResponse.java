package com.estudos.frameworks.springboot.api.dto;

import com.estudos.frameworks.springboot.domain.Article;

import java.time.Instant;

/**
 * DTO de saída JSON.
 */
public record ArticleResponse(Long id, String title, String body, Instant createdAt) {

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(article.id(), article.title(), article.body(), article.createdAt());
    }
}
