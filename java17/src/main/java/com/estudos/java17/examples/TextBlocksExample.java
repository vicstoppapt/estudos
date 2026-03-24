package com.estudos.java17.examples;

/**
 * PT: Text blocks """. Teoria: README.md.
 * EN: Text blocks """. Theory: README.md.
 */
public final class TextBlocksExample {

    private TextBlocksExample() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Alinhamento à esquerda do conteúdo define indentação removida
        // EN: Left alignment of the content defines stripped indentation
        String json = """
                {
                  "nome": "java",
                  "versao": 17
                }
                """;
        System.out.println(json);

        // meuPlayground();
    }
}
