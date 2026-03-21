package com.estudos.core.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Duas formas de contador seguro com duas threads. Conceito (thread, monitor, CAS): README.md.
 */
public final class MultithreadingIntro {

    private MultithreadingIntro() {
    }

    // Contador compartilhado sem proteção seria sujeito a race; aqui só mutamos dentro de synchronized.
    static int unsafe;
    // Objeto só usado como fechadura (monitor). Qualquer instância dedicada serve; não usar boxed literal.
    static final Object LOCK = new Object();
    // Contador atômico: incremento é uma operação indivisível no hardware (compare-and-swap).
    static final AtomicInteger ATOMIC = new AtomicInteger();

    static void incrementarSync() {
        // Só uma thread por vez entra neste bloco para o mesmo LOCK → unsafe++ não perde atualizações.
        synchronized (LOCK) {
            unsafe++;
        }
    }

    /** Espaço para experimentar (ex.: volatile sem sync). Ver README do pacote. */
    static void meuPlayground() {
    }

    public static void main(String[] args) throws InterruptedException {
        // --- Parte 1: int + synchronized ---
        // Cada thread roda 10_000 incrementos; sem synchronized o resultado seria frequentemente < 20_000.
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                incrementarSync();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                incrementarSync();
            }
        });
        t1.start();
        t2.start();
        // join() faz a thread main esperar terminar; senão poderíamos imprimir antes do resultado final.
        t1.join();
        t2.join();
        System.out.println("contador com synchronized: " + unsafe);

        // --- Parte 2: AtomicInteger ---
        // Mesmo Runnable reutilizado: as duas threads executam o mesmo laço de incrementos atômicos.
        ATOMIC.set(0);
        Runnable r = () -> {
            for (int i = 0; i < 10_000; i++) {
                ATOMIC.incrementAndGet();
            }
        };
        Thread a = new Thread(r);
        Thread b = new Thread(r);
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("AtomicInteger (2x10000): " + ATOMIC.get());

        // meuPlayground();
    }
}
