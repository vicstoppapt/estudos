package com.estudos.frameworks.springboot.domain;

/**
 * Erro quando o identificador não existe (mapeado a HTTP 404 na API).
 */
public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(Long id) {
        super("Artigo nao encontrado: id=" + id);
    }
}
