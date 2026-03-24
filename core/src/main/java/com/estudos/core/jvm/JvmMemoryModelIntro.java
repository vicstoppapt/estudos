package com.estudos.core.jvm;

/**
 * PT: Lembrete textual: stack vs heap. Teoria: README.md.
 * EN: Textual reminder: stack vs heap. Theory: README.md.
 */
public final class JvmMemoryModelIntro {

    private JvmMemoryModelIntro() {
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Text block: resumo para cruzar com o README (sem instrumentação JVM aqui).
        // EN: Text block: summary to cross-check with the README (no JVM instrumentation here).
        System.out.println("""
                Stack: primitivos locais e referências locais por frame de método.
                Heap: conteúdo dos objetos (campos de instância).
                Leia: JLS cap. 17 (memória e threads) para happens-before.""");

        // meuPlayground();
    }
}
