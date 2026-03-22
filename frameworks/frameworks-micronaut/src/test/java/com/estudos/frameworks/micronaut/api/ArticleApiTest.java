package com.estudos.frameworks.micronaut.api;

import com.estudos.frameworks.micronaut.bootstrap.Application;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(application = Application.class)
class ArticleApiTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void criarListarObterArtigo() {
        String list0 = client.toBlocking().retrieve(HttpRequest.GET("/api/articles"));
        assertEquals("[]", list0.replaceAll("\\s", ""));

        HttpResponse<String> created = client.toBlocking().exchange(
                HttpRequest.POST("/api/articles", "{\"title\":\"Intro MVC\",\"body\":\"Texto\"}")
                        .header("Content-Type", "application/json"),
                String.class);
        assertEquals(HttpStatus.CREATED, created.getStatus());
        assertTrue(created.getBody().orElse("").contains("\"id\":1"));
        assertTrue(created.getBody().orElse("").contains("Intro MVC"));

        String one = client.toBlocking().retrieve(HttpRequest.GET("/api/articles/1"));
        assertTrue(one.contains("Intro MVC"));

        String list1 = client.toBlocking().retrieve(HttpRequest.GET("/api/articles"));
        assertTrue(list1.startsWith("["));
        assertTrue(list1.contains("Intro MVC"));
    }

    @Test
    void artigoInexistenteRetorna404() {
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class,
                () -> client.toBlocking().retrieve(HttpRequest.GET("/api/articles/999")));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        String body = ex.getResponse().getBody(String.class).orElse("");
        assertTrue(body.contains("Recurso nao encontrado"));
    }

    @Test
    void tituloVazioRetorna400() {
        HttpClientResponseException ex = assertThrows(HttpClientResponseException.class,
                () -> client.toBlocking().retrieve(
                        HttpRequest.POST("/api/articles", "{\"title\":\"\",\"body\":\"x\"}")
                                .header("Content-Type", "application/json")));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        String body = ex.getResponse().getBody(String.class).orElse("").toLowerCase();
        assertTrue(body.contains("title") || body.contains("titulo") || body.contains("bad request") || body.contains("constraint"));
    }
}
