package com.estudos.basics.os;

/**
 * PT: Leitura mínima do ambiente: núcleos lógicos, processo atual. Teoria: {@code processosthreadsecpu.md} /
 * {@code processosthreadsecpu-en.md}, {@code jvmeagendamento.md} / {@code jvmeagendamento-en.md}; glossário:
 * {@code glossary-os-concurrency.md}.
 * EN: Minimal environment read: logical cores, current process. Theory: {@code processosthreadsecpu.md} /
 * {@code processosthreadsecpu-en.md}, {@code jvmeagendamento.md} / {@code jvmeagendamento-en.md}; glossary:
 * {@code glossary-os-concurrency.md}.
 */
public final class OsRuntimeSnapshot {

    private OsRuntimeSnapshot() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        System.out.println("PT: availableProcessors (núcleos lógicos visíveis à JVM): " + rt.availableProcessors());
        System.out.println("EN: availableProcessors (logical cores visible to the JVM): " + rt.availableProcessors());
        System.out.println("PT: maxMemory (bytes, limite teórico heap JVM): " + rt.maxMemory());
        System.out.println("EN: maxMemory (bytes, theoretical JVM heap limit): " + rt.maxMemory());
        System.out.println("Java version: " + Runtime.version());

        ProcessHandle ph = ProcessHandle.current();
        System.out.println("PT: pid atual: " + ph.pid());
        System.out.println("EN: current pid: " + ph.pid());
        ph.info().command().ifPresent(cmd -> {
            System.out.println("PT: comando: " + cmd);
            System.out.println("EN: command: " + cmd);
        });

        // meuPlayground();
    }
}
