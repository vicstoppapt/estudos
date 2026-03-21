package com.estudos.core.challenges.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Contador compartilhado com incremento atômico. Contexto: README em challenges/.
 */
public final class ChallengeThreadSafeCounter {

    private ChallengeThreadSafeCounter() {
    }

    static final class Counter {
        private final AtomicInteger value = new AtomicInteger();

        void increment() {
            value.incrementAndGet(); // uma operação atômica — sem lost update entre threads
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
        // esperado: 100_000
        System.out.println("esperado 100000, obtido: " + c.get());
    }
}
