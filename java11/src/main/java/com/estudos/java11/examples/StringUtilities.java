package com.estudos.java11.examples;

import java.util.stream.Collectors;

/** Novos métodos String. Teoria: README.md. */
public final class StringUtilities {

    private StringUtilities() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        String s = "  \u2000ola\nmundo  ";
        // true se só whitespace (inclui Unicode)
        System.out.println("isBlank: " + "   ".isBlank());
        // remove whitespace leading/trailing Unicode
        System.out.println("strip: '" + s.strip() + "'");
        // Stream de linhas sem \n finais vazias extras
        System.out.println("lines: " + s.lines().collect(Collectors.toList()));
        System.out.println("repeat: " + "ab".repeat(3));

        // meuPlayground();
    }
}
