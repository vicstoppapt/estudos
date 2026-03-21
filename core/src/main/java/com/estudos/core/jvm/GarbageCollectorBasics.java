package com.estudos.core.jvm;

/**
 * Heap aproximado via Runtime; soltar referência. Teoria: README.md.
 */
public final class GarbageCollectorBasics {

    private GarbageCollectorBasics() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        // freeMemory/totalMemory são aproximações; podem mudar entre chamadas
        long antes = rt.totalMemory() - rt.freeMemory();
        byte[] blob = new byte[1024 * 1024];
        long depois = rt.totalMemory() - rt.freeMemory();
        System.out.println("heap aprox. usado antes: " + antes + " bytes");
        System.out.println("heap aprox. usado depois de 1 MiB alocado: " + depois + " bytes");
        blob = null; // sem outras refs, array de 1 MiB pode ser coletado depois
        System.out.println("(referencia ao array solta; objeto pode ser coletado em ciclo futuro — nao garantimos quando)");

        // meuPlayground();
    }
}
