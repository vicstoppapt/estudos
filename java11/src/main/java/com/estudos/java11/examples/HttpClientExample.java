package com.estudos.java11.examples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * PT: HttpClient síncrono. Teoria: README.md.
 * EN: Synchronous HttpClient. Theory: README.md.
 */
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
        // PT: Bloqueia até cabeçalho+corpo (aqui corpo como String)
        // EN: Blocks until headers+body (here body as String)
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("status: " + resp.statusCode());
        String body = resp.body();
        int n = Math.min(120, body.length());
        System.out.println("PT: primeiros " + n + " chars: " + body.substring(0, n) + " | EN: first " + n + " chars: "
                + body.substring(0, n));

        // meuPlayground();
    }
}
