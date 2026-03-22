package com.estudos.frameworks.quarkus.api;

import com.estudos.frameworks.quarkus.api.dto.ArticleResponse;
import com.estudos.frameworks.quarkus.api.dto.CreateArticleRequest;
import com.estudos.frameworks.quarkus.application.ArticleService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleResource {

    @Inject
    ArticleService articleService;

    @POST
    public Response create(@Valid CreateArticleRequest request) {
        var article = articleService.create(request.title(), request.body());
        return Response.status(Response.Status.CREATED).entity(ArticleResponse.from(article)).build();
    }

    @GET
    @Path("/{id}")
    public ArticleResponse getById(@PathParam("id") Long id) {
        return ArticleResponse.from(articleService.getById(id));
    }

    @GET
    public List<ArticleResponse> list() {
        return articleService.findAll().stream().map(ArticleResponse::from).toList();
    }
}
