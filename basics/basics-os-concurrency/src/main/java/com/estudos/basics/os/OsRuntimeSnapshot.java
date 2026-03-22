package com.estudos.basics.os;

/**
 * Leitura mínima do ambiente: núcleos lógicos, processo atual. Teoria: {@code processosthreadsecpu.md},
 * {@code jvmeagendamento.md}.
 */
public final class OsRuntimeSnapshot {

    private OsRuntimeSnapshot() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        System.out.println("availableProcessors (núcleos lógicos visíveis à JVM): " + rt.availableProcessors());
        System.out.println("maxMemory (bytes, limite teórico heap JVM): " + rt.maxMemory());
        System.out.println("Java version: " + Runtime.version());

        ProcessHandle ph = ProcessHandle.current();
        System.out.println("pid atual: " + ph.pid());
        ph.info().command().ifPresent(cmd -> System.out.println("comando: " + cmd));

        // meuPlayground();
    }
}
