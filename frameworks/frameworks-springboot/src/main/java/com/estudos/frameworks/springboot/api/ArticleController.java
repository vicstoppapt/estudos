package com.estudos.frameworks.springboot.api;

import com.estudos.frameworks.springboot.api.dto.ArticleResponse;
import com.estudos.frameworks.springboot.api.dto.CreateArticleRequest;
import com.estudos.frameworks.springboot.application.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Camada Web MVC: HTTP, DTOs, delegação ao serviço.
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponse create(@Valid @RequestBody CreateArticleRequest request) {
        var article = articleService.create(request.title(), request.body());
        return ArticleResponse.from(article);
    }

    @GetMapping("/{id}")
    public ArticleResponse getById(@PathVariable Long id) {
        return ArticleResponse.from(articleService.getById(id));
    }

    @GetMapping
    public List<ArticleResponse> list() {
        return articleService.findAll().stream().map(ArticleResponse::from).toList();
    }
}
