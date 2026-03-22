package com.estudos.frameworks.quarkus.domain.repository;

import com.estudos.frameworks.quarkus.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findAll();
}
