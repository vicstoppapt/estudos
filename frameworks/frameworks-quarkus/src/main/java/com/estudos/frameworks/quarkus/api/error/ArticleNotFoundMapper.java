package com.estudos.frameworks.quarkus.api.error;

import com.estudos.frameworks.quarkus.domain.ArticleNotFoundException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.LinkedHashMap;
import java.util.Map;

@Provider
public class ArticleNotFoundMapper implements ExceptionMapper<ArticleNotFoundException> {

    @Inject
    UriInfo uriInfo;

    @Override
    public Response toResponse(ArticleNotFoundException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", "Recurso nao encontrado");
        body.put("detail", exception.getMessage());
        body.put("status", Response.Status.NOT_FOUND.getStatusCode());
        body.put("instance", uriInfo.getRequestUri().toString());
        return Response.status(Response.Status.NOT_FOUND).entity(body).build();
    }
}
