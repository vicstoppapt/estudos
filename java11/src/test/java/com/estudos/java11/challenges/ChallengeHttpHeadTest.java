package com.estudos.java11.challenges;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChallengeHttpHeadTest {

    @Mock
    private HttpClient client;

    @Mock
    private HttpResponse<Void> response;

    @Test
    void statusHeadUsaCliente() throws IOException, InterruptedException {
        when(response.statusCode()).thenReturn(204);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);
        assertThat(ChallengeHttpHead.statusHead(client, URI.create("http://localhost/"))).isEqualTo(204);
    }
}
