package com.estudos.core.challenges.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * PT: Contador compartilhado com incremento atômico. Contexto: README em challenges/.
 * EN: Shared counter with atomic increment. Context: README under challenges/.
 */
public final class ChallengeThreadSafeCounter {

    private ChallengeThreadSafeCounter() {
    }

    static final class Counter {
        private final AtomicInteger value = new AtomicInteger();

        void increment() {
            // PT: Uma operação atômica — sem lost update entre threads.
            // EN: One atomic operation — no lost updates between threads.
            value.incrementAndGet();
        }

        int get() {
            return value.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Runnable job = () -> {
            for (int i = 0; i < 50_000; i++) {
                c.increment();
            }
        };
        Thread t1 = new Thread(job);
        Thread t2 = new Thread(job);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // PT: Esperado: 100_000.
        // EN: Expected: 100_000.
        System.out.println("esperado 100000, obtido: " + c.get());
    }
}
