package com.estudos.java21.examples;

import java.util.concurrent.Executors;

/** Virtual threads. Teoria: README.md. */
public final class VirtualThreadsExample {

    private VirtualThreadsExample() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) throws InterruptedException {
        // Cada submit pode mapear para nova virtual thread
        try (var ex = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                int n = i; // cópia efetivamente final para lambda
                ex.submit(() -> System.out.println("vt " + n + " " + Thread.currentThread()));
            }
        }

        // meuPlayground();
    }
}
