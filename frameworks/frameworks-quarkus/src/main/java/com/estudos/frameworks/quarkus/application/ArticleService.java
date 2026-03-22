package com.estudos.frameworks.quarkus.application;

import com.estudos.frameworks.quarkus.domain.Article;

import java.util.List;

public interface ArticleService {

    Article create(String title, String body);

    Article getById(Long id);

    List<Article> findAll();
}
