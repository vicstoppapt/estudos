package com.estudos.java21.examples;

import java.util.concurrent.Executors;

/**
 * PT: Virtual threads. Teoria: README.md.
 * EN: Virtual threads. Theory: README.md.
 */
public final class VirtualThreadsExample {

    private VirtualThreadsExample() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) throws InterruptedException {
        // PT: Cada submit pode mapear para nova virtual thread
        // EN: Each submit may map to a new virtual thread
        try (var ex = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                // PT: cópia efetivamente final para lambda
                // EN: effectively final copy for the lambda
                int n = i;
                ex.submit(() -> System.out.println("vt " + n + " " + Thread.currentThread()));
            }
        }

        // meuPlayground();
    }
}
