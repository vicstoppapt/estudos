package com.estudos.frameworks.quarkus.domain;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(Long id) {
        super("Artigo nao encontrado: id=" + id);
    }
}
