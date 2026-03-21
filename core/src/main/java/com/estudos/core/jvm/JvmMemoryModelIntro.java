package com.estudos.core.jvm;

/**
 * Lembrete textual: stack vs heap. Teoria: README.md.
 */
public final class JvmMemoryModelIntro {

    private JvmMemoryModelIntro() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // Text block: resumo para cruzar com o README (sem instrumentação JVM aqui)
        System.out.println("""
                Stack: primitivos locais e referências locais por frame de método.
                Heap: conteúdo dos objetos (campos de instância).
                Leia: JLS cap. 17 (memória e threads) para happens-before.""");

        // meuPlayground();
    }
}
