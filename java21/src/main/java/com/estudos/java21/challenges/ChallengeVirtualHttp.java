package com.estudos.java21.challenges;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

/** GET paralelo injetável. README em challenges/. */
public final class ChallengeVirtualHttp {

    private ChallengeVirtualHttp() {
    }

    static List<Integer> statusesGet(HttpClient client, Executor executor, List<URI> uris) {
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (URI uri : uris) {
            futures.add(CompletableFuture.supplyAsync(() -> {
                try {
                    HttpRequest req = HttpRequest.newBuilder().uri(uri).GET().build();
                    return client.send(req, HttpResponse.BodyHandlers.discarding()).statusCode();
                } catch (IOException | InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new CompletionException(e);
                }
            }, executor));
        }
        List<Integer> out = new ArrayList<>();
        for (CompletableFuture<Integer> f : futures) {
            out.add(f.join()); // propaga CompletionException se falhou async
        }
        return out;
    }

    public static void main(String[] args) {
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            HttpClient client = HttpClient.newBuilder().build();
            List<URI> uris = List.of(
                    URI.create("https://example.com"),
                    URI.create("https://example.com"),
                    URI.create("https://example.com"));
            List<Integer> codes = statusesGet(client, executor, uris);
            codes.forEach(c -> System.out.println("status: " + c));
        }
    }
}
