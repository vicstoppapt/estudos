package com.estudos.java21.challenges;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Executor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChallengeVirtualHttpTest {

    @Mock
    private HttpClient client;

    @Mock
    private HttpResponse<Void> response;

    @Test
    void paraleloUsaClienteMockado() throws IOException, InterruptedException {
        when(response.statusCode()).thenReturn(200);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);
        Executor direct = Runnable::run;
        List<Integer> codes = ChallengeVirtualHttp.statusesGet(client, direct,
                List.of(URI.create("http://a"), URI.create("http://b")));
        assertThat(codes).containsExactly(200, 200);
    }
}
