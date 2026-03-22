package com.estudos.frameworks.springboot.domain.repository;

import com.estudos.frameworks.springboot.domain.Article;

import java.util.List;
import java.util.Optional;

/**
 * Porta de persistência (contrato no domínio; implementação na infraestrutura).
 */
public interface ArticleRepository {

    Article save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findAll();
}
