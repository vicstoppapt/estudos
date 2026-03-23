package com.estudos.basics.os;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Exemplos mínimos: leitura de paralelismo, threads nomeadas, pool dimensionado a {@code availableProcessors}.
 * Teoria: {@code processadorcoresethreads.md}.
 */
public final class ProcessorThreadExamples {

    private ProcessorThreadExamples() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) throws InterruptedException {
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println("=== Processador visto pela JVM ===");
        System.out.println("Runtime.getRuntime().availableProcessors() = " + n);
        System.out.println("(Tipicamente = CPUs logicas agendaveis; em contentores pode diferir da quota real.)");
        System.out.println();

        ForkJoinPool common = ForkJoinPool.commonPool();
        System.out.println("ForkJoinPool.commonPool().getParallelism() = " + common.getParallelism());
        System.out.println();

        System.out.println("=== Tres threads de SO a correr em paralelo (curto) ===");
        CountDownLatch done = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            int id = i;
            Thread t = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " a correr");
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

        System.out.println("=== Executor fixo com tamanho = availableProcessors (padrao CPU-bound) ===");
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
                    System.out.println("tarefa " + task + " concluida (busy work), thread=" + Thread.currentThread().getName());
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
        System.out.println("Para I/O-bound, o pool costuma ser maior que " + n + "; para CPU-bound puro, ~" + n + " e habitual.");
        // meuPlayground();
    }
}
