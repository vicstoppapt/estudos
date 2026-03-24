package com.estudos.java11.examples;

import java.util.stream.Collectors;

/**
 * PT: Novos métodos String. Teoria: README.md.
 * EN: New String methods. Theory: README.md.
 */
public final class StringUtilities {

    private StringUtilities() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        String s = "  \u2000ola\nmundo  ";
        // PT: true se só whitespace (inclui Unicode)
        // EN: true if only whitespace (includes Unicode)
        System.out.println("PT: isBlank: " + "   ".isBlank() + " | EN: isBlank: " + "   ".isBlank());
        // PT: remove whitespace leading/trailing Unicode
        // EN: strip leading/trailing Unicode whitespace
        System.out.println("PT: strip: '" + s.strip() + "' | EN: strip: '" + s.strip() + "'");
        // PT: Stream de linhas sem \n finais vazias extras
        // EN: Stream of lines without extra empty trailing newlines
        System.out.println("PT: lines: " + s.lines().collect(Collectors.toList()) + " | EN: lines: "
                + s.lines().collect(Collectors.toList()));
        System.out.println("PT: repeat: " + "ab".repeat(3) + " | EN: repeat: " + "ab".repeat(3));

        // meuPlayground();
    }
}
