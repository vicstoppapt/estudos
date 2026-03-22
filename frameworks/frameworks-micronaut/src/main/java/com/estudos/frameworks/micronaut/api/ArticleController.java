package com.estudos.frameworks.micronaut.api;

import com.estudos.frameworks.micronaut.api.dto.ArticleResponse;
import com.estudos.frameworks.micronaut.api.dto.CreateArticleRequest;
import com.estudos.frameworks.micronaut.application.ArticleService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;

import java.util.List;

@Validated
@Controller("/api/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleController {

    private final ArticleService articleService;

    ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Post
    public HttpResponse<ArticleResponse> create(@Body @Valid CreateArticleRequest request) {
        var article = articleService.create(request.title(), request.body());
        return HttpResponse.created(ArticleResponse.from(article));
    }

    @Get("/{id}")
    public ArticleResponse getById(@PathVariable Long id) {
        return ArticleResponse.from(articleService.getById(id));
    }

    @Get
    public List<ArticleResponse> list() {
        return articleService.findAll().stream().map(ArticleResponse::from).toList();
    }
}
