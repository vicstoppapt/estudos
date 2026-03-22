package com.estudos.frameworks.quarkus.application;

import com.estudos.frameworks.quarkus.domain.Article;
import com.estudos.frameworks.quarkus.domain.ArticleNotFoundException;
import com.estudos.frameworks.quarkus.domain.repository.ArticleRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@ApplicationScoped
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
