package com.estudos.frameworks.quarkus.infrastructure.persistence;

import com.estudos.frameworks.quarkus.domain.Article;
import com.estudos.frameworks.quarkus.domain.repository.ArticleRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class InMemoryArticleRepository implements ArticleRepository {

    private final Map<Long, Article> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public Article save(Article article) {
        Long id = article.id() != null ? article.id() : idSequence.getAndIncrement();
        Article persisted = new Article(id, article.title(), article.body(), article.createdAt());
        store.put(id, persisted);
        return persisted;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(store.values());
    }
}
