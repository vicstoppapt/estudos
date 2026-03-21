package com.estudos.core.imperative;

/**
 * == (identidade) vs equals (conteúdo); pool. Teoria: README.md.
 */
public final class StringImmutability {

    private StringImmutability() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // Literais iguais podem ser o mesmo objeto no pool → a == b frequentemente true
        String a = "java";
        String b = "java";
        // new sempre cria outro objeto no heap (conteúdo pode ser igual ao literal)
        String c = new String("java");

        System.out.println("a == b (literais iguais): " + (a == b));
        System.out.println("a == c (literal vs new): " + (a == c));
        System.out.println("a.equals(c): " + a.equals(c));
        // intern() devolve a entrada do pool equivalente — pode tornar == true com o literal
        System.out.println("a == c.intern(): " + (a == c.intern()));

        // s passa a referenciar uma NOVA String; a anterior "hello" fica elegível ao GC se não houver outra ref
        String s = "hello";
        s = s + " world";
        System.out.println("nova String apos +: " + s);

        // meuPlayground();
    }
}
