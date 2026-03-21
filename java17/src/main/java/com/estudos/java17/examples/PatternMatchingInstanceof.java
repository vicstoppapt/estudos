package com.estudos.java17.examples;

/** instanceof com binding. Teoria: README.md. */
public final class PatternMatchingInstanceof {

    private PatternMatchingInstanceof() {
    }

    static String descrever(Object o) {
        // s só existe se for String E length > 2
        if (o instanceof String s && s.length() > 2) {
            return "string longa: " + s;
        }
        if (o instanceof Integer i) {
            return "int: " + i;
        }
        return "outro";
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
