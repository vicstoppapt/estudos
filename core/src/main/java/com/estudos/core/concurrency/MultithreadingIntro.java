package com.estudos.core.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * PT: Duas formas de contador seguro com duas threads. Conceito (thread, monitor, CAS): README.md.
 * EN: Two ways to implement a safe counter with two threads. Concepts (thread, monitor, CAS): README.md.
 */
public final class MultithreadingIntro {

    private MultithreadingIntro() {
    }

    // PT: Contador compartilhado sem proteção seria sujeito a race; aqui só mutamos dentro de synchronized.
    // EN: A shared counter without protection would be prone to races; here we only mutate inside synchronized.
    static int unsafe;
    // PT: Objeto só usado como fechadura (monitor). Qualquer instância dedicada serve; não usar boxed literal.
    // EN: Object used only as a lock (monitor). Any dedicated instance works; do not use a boxed literal.
    static final Object LOCK = new Object();
    // PT: Contador atômico: incremento é uma operação indivisível no hardware (compare-and-swap).
    // EN: Atomic counter: increment is one indivisible hardware operation (compare-and-swap).
    static final AtomicInteger ATOMIC = new AtomicInteger();

    static void incrementarSync() {
        // PT: Só uma thread por vez entra neste bloco para o mesmo LOCK → unsafe++ não perde atualizações.
        // EN: Only one thread at a time enters this block for the same LOCK → unsafe++ does not lose updates.
        synchronized (LOCK) {
            unsafe++;
        }
    }

    /**
     * PT: Espaço para experimentar (ex.: volatile sem sync). Ver README do pacote.
     * EN: Space to experiment (e.g. volatile without sync). See package README.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) throws InterruptedException {
        // PT: --- Parte 1: int + synchronized ---
        // EN: --- Part 1: int + synchronized ---
        // PT: Cada thread roda 10_000 incrementos; sem synchronized o resultado seria frequentemente < 20_000.
        // EN: Each thread runs 10_000 increments; without synchronized the result would often be < 20_000.
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
        // PT: join() faz a thread main esperar terminar; senão poderíamos imprimir antes do resultado final.
        // EN: join() makes the main thread wait to finish; otherwise we might print before the final result.
        t1.join();
        t2.join();
        System.out.println("contador com synchronized: " + unsafe);

        // PT: --- Parte 2: AtomicInteger ---
        // EN: --- Part 2: AtomicInteger ---
        // PT: Mesmo Runnable reutilizado: as duas threads executam o mesmo laço de incrementos atômicos.
        // EN: Same Runnable reused: both threads run the same loop of atomic increments.
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
