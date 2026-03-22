package com.estudos.frameworks.micronaut.domain;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(Long id) {
        super("Artigo nao encontrado: id=" + id);
    }
}
