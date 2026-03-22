package com.estudos.frameworks.micronaut.api.error;

import com.estudos.frameworks.micronaut.domain.ArticleNotFoundException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import java.util.Map;

@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ArticleNotFoundExceptionHandler implements ExceptionHandler<ArticleNotFoundException, HttpResponse<Map<String, Object>>> {

    @Override
    public HttpResponse<Map<String, Object>> handle(HttpRequest request, ArticleNotFoundException exception) {
        Map<String, Object> body = Map.of(
                "title", "Recurso nao encontrado",
                "detail", exception.getMessage(),
                "status", HttpStatus.NOT_FOUND.getCode(),
                "instance", request.getUri().toString()
        );
        return HttpResponse.status(HttpStatus.NOT_FOUND).body(body);
    }
}
