package com.estudos.frameworks.micronaut.application;

import com.estudos.frameworks.micronaut.domain.Article;

import java.util.List;

public interface ArticleService {

    Article create(String title, String body);

    Article getById(Long id);

    List<Article> findAll();
}
