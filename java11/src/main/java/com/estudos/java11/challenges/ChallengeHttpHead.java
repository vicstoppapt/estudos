package com.estudos.java11.challenges;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/** HEAD + status. README em challenges/. */
public final class ChallengeHttpHead {

    private ChallengeHttpHead() {
    }

    static int statusHead(HttpClient client, URI uri) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();
        // discarding() — não aloca String do corpo (HEAD costuma vir vazio mesmo)
        HttpResponse<Void> resp = client.send(req, HttpResponse.BodyHandlers.discarding());
        return resp.statusCode();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        int code = statusHead(client, URI.create("https://example.com"));
        System.out.println("status HEAD: " + code);
    }
}
