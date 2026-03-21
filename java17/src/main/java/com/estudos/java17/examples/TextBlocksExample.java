package com.estudos.java17.examples;

/** Text blocks """. Teoria: README.md. */
public final class TextBlocksExample {

    private TextBlocksExample() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // Alinhamento à esquerda do conteúdo define indentação removida
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
