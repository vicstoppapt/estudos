package com.estudos.frameworks.quarkus.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleResourceIT {

    @Test
    @Order(1)
    void listaInicialmenteVazia() {
        given()
                .when().get("/api/articles")
                .then()
                .statusCode(200)
                .body("$", hasSize(0));
    }

    @Test
    @Order(2)
    void criarEListar() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"title\":\"Intro MVC\",\"body\":\"Texto\"}")
                .when().post("/api/articles")
                .then()
                .statusCode(201)
                .body("id", equalTo(1))
                .body("title", equalTo("Intro MVC"));

        given()
                .when().get("/api/articles/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Intro MVC"));

        given()
                .when().get("/api/articles")
                .then()
                .statusCode(200)
                .body("$", hasSize(1));
    }

    @Test
    @Order(3)
    void artigoInexistente404() {
        given()
                .when().get("/api/articles/999")
                .then()
                .statusCode(404)
                .body("title", equalTo("Recurso nao encontrado"));
    }

    @Test
    @Order(4)
    void tituloVazio400() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"title\":\"\",\"body\":\"x\"}")
                .when().post("/api/articles")
                .then()
                .statusCode(400)
                .body("title", equalTo("Payload invalido"));
    }
}
