package com.estudos.frameworks.quarkus.api.error;

import jakarta.validation.ConstraintViolationException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Inject
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Map<String, String> fieldErrors = exception.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        v -> lastNode(v.getPropertyPath().toString()),
                        v -> v.getMessage() == null ? "invalido" : v.getMessage(),
                        (a, b) -> a,
                        LinkedHashMap::new));
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", "Payload invalido");
        body.put("detail", "Erro de validacao");
        body.put("status", Response.Status.BAD_REQUEST.getStatusCode());
        body.put("instance", uriInfo.getRequestUri().toString());
        body.put("fieldErrors", fieldErrors);
        return Response.status(Response.Status.BAD_REQUEST).entity(body).build();
    }

    private static String lastNode(String path) {
        int i = path.lastIndexOf('.');
        return i >= 0 ? path.substring(i + 1) : path;
    }
}
