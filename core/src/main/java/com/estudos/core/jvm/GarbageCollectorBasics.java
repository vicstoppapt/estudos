package com.estudos.core.jvm;

/**
 * PT: Heap aproximado via Runtime; soltar referência. Teoria: README.md.
 * EN: Approximate heap via Runtime; dropping references. Theory: README.md.
 */
public final class GarbageCollectorBasics {

    private GarbageCollectorBasics() {
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        // PT: freeMemory/totalMemory são aproximações; podem mudar entre chamadas.
        // EN: freeMemory/totalMemory are approximations; they may change between calls.
        long antes = rt.totalMemory() - rt.freeMemory();
        byte[] blob = new byte[1024 * 1024];
        long depois = rt.totalMemory() - rt.freeMemory();
        System.out.println("heap aprox. usado antes: " + antes + " bytes");
        System.out.println("heap aprox. usado depois de 1 MiB alocado: " + depois + " bytes");
        // PT: Sem outras refs, array de 1 MiB pode ser coletado depois.
        // EN: With no other refs, the 1 MiB array may be collected later.
        blob = null;
        System.out.println("(referencia ao array solta; objeto pode ser coletado em ciclo futuro — nao garantimos quando)");

        // meuPlayground();
    }
}
