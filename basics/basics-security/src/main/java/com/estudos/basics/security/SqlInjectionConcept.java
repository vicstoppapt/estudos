package com.estudos.basics.security;

/**
 * Ilustração **educativa** de SQL injection: mostra um *payload* típico e a diferença entre
 * concatenação (vulnerável) e {@code PreparedStatement} (padrão seguro). **Não** executa SQL
 * contra qualquer base de dados. Teoria: {@code injecaosqleddos.md}.
 */
public final class SqlInjectionConcept {

    private SqlInjectionConcept() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        String userInputMalicioso = "' OR '1'='1' -- ";

        System.out.println("--- Payload de exemplo (ataque) ---");
        System.out.println(userInputMalicioso);

        String queryVulneravel = "SELECT * FROM users WHERE name = '" + userInputMalicioso + "' AND active = 1";
        System.out.println("\n--- Anti-padrão: SQL montado com concatenação ---");
        System.out.println(queryVulneravel);

        System.out.println("\n--- Padrão seguro (conceito) ---");
        System.out.println("SQL fixo: SELECT * FROM users WHERE name = ? AND active = 1");
        System.out.println("PreparedStatement.setString(1, userInput) — o valor nunca é interpretado como SQL.");

        // meuPlayground();
    }
}
