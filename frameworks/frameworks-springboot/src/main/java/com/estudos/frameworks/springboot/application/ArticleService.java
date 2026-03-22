package com.estudos.frameworks.springboot.application;

import com.estudos.frameworks.springboot.domain.Article;

import java.util.List;

/**
 * Caso de uso / application service.
 */
public interface ArticleService {

    Article create(String title, String body);

    Article getById(Long id);

    List<Article> findAll();
}
