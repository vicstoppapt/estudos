package com.estudos.basics.security;

/**
 * PT: Ilustração **educativa** de SQL injection: mostra um *payload* típico e a diferença entre
 * concatenação (vulnerável) e {@code PreparedStatement} (padrão seguro). **Não** executa SQL
 * contra qualquer base de dados. Teoria: {@code injecaosqleddos.md}.
 * EN: **Educational** illustration of SQL injection: shows a typical *payload* and the difference between
 * concatenation (vulnerable) and {@code PreparedStatement} (safe pattern). Does **not** run SQL
 * against any database. Theory: {@code injecaosqleddos.md}.
 */
public final class SqlInjectionConcept {

    private SqlInjectionConcept() {
    }

    static void meuPlayground() {
    }

    public static void main(String[] args) {
        String userInputMalicioso = "' OR '1'='1' -- ";

        System.out.println("PT: --- Payload de exemplo (ataque) ---");
        System.out.println("EN: --- Example payload (attack) ---");
        System.out.println(userInputMalicioso);

        String queryVulneravel = "SELECT * FROM users WHERE name = '" + userInputMalicioso + "' AND active = 1";
        System.out.println();
        System.out.println("PT: --- Anti-padrão: SQL montado com concatenação ---");
        System.out.println("EN: --- Anti-pattern: SQL built with concatenation ---");
        System.out.println(queryVulneravel);

        System.out.println();
        System.out.println("PT: --- Padrão seguro (conceito) ---");
        System.out.println("EN: --- Safe pattern (concept) ---");
        System.out.println("PT: SQL fixo: SELECT * FROM users WHERE name = ? AND active = 1");
        System.out.println("EN: Fixed SQL: SELECT * FROM users WHERE name = ? AND active = 1");
        System.out.println("PT: PreparedStatement.setString(1, userInput) — o valor nunca é interpretado como SQL.");
        System.out.println("EN: PreparedStatement.setString(1, userInput) — the value is never interpreted as SQL.");

        // meuPlayground();
    }
}
