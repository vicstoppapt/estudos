package com.estudos.core.challenges.concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ChallengeThreadSafeCounterTest {

    @Test
    void contadorSobConcurrencia() throws InterruptedException {
        ChallengeThreadSafeCounter.Counter c = new ChallengeThreadSafeCounter.Counter();
        int threads = 4;
        int porThread = 25_000;
        Thread[] arr = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            arr[i] = new Thread(() -> {
                for (int j = 0; j < porThread; j++) {
                    c.increment();
                }
            });
        }
        for (Thread t : arr) {
            t.start();
        }
        for (Thread t : arr) {
            t.join();
        }
        assertThat(c.get()).isEqualTo(threads * porThread);
    }
}
