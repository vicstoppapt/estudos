package com.estudos.basics.os;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * PT: Exemplos mínimos: leitura de paralelismo, threads nomeadas, pool dimensionado a {@code availableProcessors}.
 * Teoria: {@code processadorcoresethreads.md} / {@code processadorcoresethreads-en.md}; glossário: {@code glossary-os-concurrency.md}.
 * EN: Minimal examples: parallelism readout, named threads, pool sized to {@code availableProcessors}.
 * Theory: {@code processadorcoresethreads.md} / {@code processadorcoresethreads-en.md}; glossary: {@code glossary-os-concurrency.md}.
 */
public final class ProcessorThreadExamples {

    private ProcessorThreadExamples() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) throws InterruptedException {
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println("PT: === Processador visto pela JVM ===");
        System.out.println("EN: === Processor as seen by the JVM ===");
        System.out.println("PT: Runtime.getRuntime().availableProcessors() = " + n);
        System.out.println("EN: Runtime.getRuntime().availableProcessors() = " + n);
        System.out.println("PT: (Tipicamente = CPUs logicas agendaveis; em contentores pode diferir da quota real.)");
        System.out.println("EN: (Typically schedulable logical CPUs; in containers may differ from actual quota.)");
        System.out.println();

        ForkJoinPool common = ForkJoinPool.commonPool();
        System.out.println("ForkJoinPool.commonPool().getParallelism() = " + common.getParallelism());
        System.out.println();

        System.out.println("PT: === Tres threads de SO a correr em paralelo (curto) ===");
        System.out.println("EN: === Three OS threads running in parallel (brief) ===");
        CountDownLatch done = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            int id = i;
            Thread t = new Thread(() -> {
                try {
                    System.out.println("PT: " + Thread.currentThread().getName() + " a correr");
                    System.out.println("EN: " + Thread.currentThread().getName() + " running");
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            }, "worker-demo-" + id);
            t.start();
        }
        done.await();
        System.out.println();

        System.out.println("PT: === Executor fixo com tamanho = availableProcessors (padrao CPU-bound) ===");
        System.out.println("EN: === Fixed executor with size = availableProcessors (CPU-bound default) ===");
        int poolSize = Math.max(1, n);
        ExecutorService pool = Executors.newFixedThreadPool(poolSize, r -> {
            Thread t = new Thread(r, "cpu-pool-" + System.nanoTime());
            t.setDaemon(true);
            return t;
        });
        try {
            for (int i = 0; i < poolSize; i++) {
                final int task = i;
                pool.submit(() -> {
                    double x = 0;
                    for (int k = 0; k < 500_000; k++) {
                        x += Math.sin(k) * Math.cos(k);
                    }
                    System.out.println("PT: tarefa " + task + " concluida (busy work), thread=" + Thread.currentThread().getName());
                    System.out.println("EN: task " + task + " done (busy work), thread=" + Thread.currentThread().getName());
                    return x;
                });
            }
        } finally {
            pool.shutdown();
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        }

        System.out.println();
        System.out.println("PT: Para I/O-bound, o pool costuma ser maior que " + n + "; para CPU-bound puro, ~" + n + " e habitual.");
        System.out.println("EN: For I/O-bound work, the pool is usually larger than " + n + "; for pure CPU-bound, ~" + n + " is common.");
        // meuPlayground();
    }
}
