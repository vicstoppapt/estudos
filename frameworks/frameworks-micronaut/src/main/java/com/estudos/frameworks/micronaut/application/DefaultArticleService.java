package com.estudos.frameworks.micronaut.application;

import com.estudos.frameworks.micronaut.domain.Article;
import com.estudos.frameworks.micronaut.domain.ArticleNotFoundException;
import com.estudos.frameworks.micronaut.domain.repository.ArticleRepository;
import jakarta.inject.Singleton;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Singleton
public class DefaultArticleService implements ArticleService {

    private final ArticleRepository articleRepository;

    DefaultArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article create(String title, String body) {
        String safeBody = body == null ? "" : body;
        Article draft = new Article(null, title, safeBody, Instant.now());
        return articleRepository.save(draft);
    }

    @Override
    public Article getById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll().stream()
                .sorted(Comparator.comparing(Article::id))
                .toList();
    }
}
