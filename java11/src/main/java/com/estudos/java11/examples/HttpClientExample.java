package com.estudos.java11.examples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/** HttpClient síncrono. Teoria: README.md. */
public final class HttpClientExample {

    private HttpClientExample() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .GET()
                .build();
        // Bloqueia até cabeçalho+corpo (aqui corpo como String)
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("status: " + resp.statusCode());
        System.out.println("primeiros 120 chars: " + resp.body().substring(0, Math.min(120, resp.body().length())));

        // meuPlayground();
    }
}
