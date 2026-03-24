package com.estudos.java11.challenges;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * PT: HEAD + status. README em challenges/.
 * EN: HEAD + status. README in challenges/.
 */
public final class ChallengeHttpHead {

    private ChallengeHttpHead() {
    }

    static int statusHead(HttpClient client, URI uri) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();
        // PT: discarding() — não aloca String do corpo (HEAD costuma vir vazio mesmo)
        // EN: discarding() — does not allocate a body String (HEAD bodies are usually empty)
        HttpResponse<Void> resp = client.send(req, HttpResponse.BodyHandlers.discarding());
        return resp.statusCode();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        int code = statusHead(client, URI.create("https://example.com"));
        System.out.println("PT: status HEAD: " + code + " | EN: HEAD status: " + code);
    }
}
