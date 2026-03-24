package com.estudos.core.imperative;

/**
 * PT: == (identidade) vs equals (conteúdo); pool. Teoria: README.md.
 * EN: == (identity) vs equals (content); pool. Theory: README.md.
 */
public final class StringImmutability {

    private StringImmutability() {
    }

    /**
     * PT: Playground; descomente no main.
     * EN: Playground; uncomment in main.
     */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Literais iguais podem ser o mesmo objeto no pool → a == b frequentemente true.
        // EN: Equal literals may be the same object in the pool → a == b is often true.
        String a = "java";
        String b = "java";
        // PT: new sempre cria outro objeto no heap (conteúdo pode ser igual ao literal).
        // EN: new always creates another object on the heap (content may equal the literal).
        String c = new String("java");

        System.out.println("a == b (literais iguais): " + (a == b));
        System.out.println("a == c (literal vs new): " + (a == c));
        System.out.println("a.equals(c): " + a.equals(c));
        // PT: intern() devolve a entrada do pool equivalente — pode tornar == true com o literal.
        // EN: intern() returns the equivalent pool entry — can make == true with the literal.
        System.out.println("a == c.intern(): " + (a == c.intern()));

        // PT: s passa a referenciar uma NOVA String; a anterior "hello" fica elegível ao GC se não houver outra ref.
        // EN: s now refers to a NEW String; the previous "hello" becomes GC-eligible if nothing else references it.
        String s = "hello";
        s = s + " world";
        System.out.println("nova String apos +: " + s);

        // meuPlayground();
    }
}
