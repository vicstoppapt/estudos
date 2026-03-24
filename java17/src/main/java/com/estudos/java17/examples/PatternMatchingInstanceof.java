package com.estudos.java17.examples;

/**
 * PT: instanceof com binding. Teoria: README.md.
 * EN: instanceof with binding. Theory: README.md.
 */
public final class PatternMatchingInstanceof {

    private PatternMatchingInstanceof() {
    }

    static String descrever(Object o) {
        // PT: s só existe se for String E length > 2
        // EN: s exists only if it is a String AND length > 2
        if (o instanceof String s && s.length() > 2) {
            return "PT: string longa: " + s + " | EN: long string: " + s;
        }
        if (o instanceof Integer i) {
            return "PT: int: " + i + " | EN: int: " + i;
        }
        return "PT: outro | EN: other";
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        System.out.println(descrever("abc"));
        System.out.println(descrever("x"));
        System.out.println(descrever(7));

        // meuPlayground();
    }
}
