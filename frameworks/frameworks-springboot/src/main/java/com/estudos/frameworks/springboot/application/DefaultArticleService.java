package com.estudos.frameworks.springboot.application;

import com.estudos.frameworks.springboot.domain.Article;
import com.estudos.frameworks.springboot.domain.ArticleNotFoundException;
import com.estudos.frameworks.springboot.domain.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
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
