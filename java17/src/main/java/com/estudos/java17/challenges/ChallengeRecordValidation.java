package com.estudos.java17.challenges;

/**
 * PT: Record com validação. README em challenges/.
 * EN: Record with validation. README in challenges/.
 */
public final class ChallengeRecordValidation {

    private ChallengeRecordValidation() {
    }

    public record Email(String valor) {
        public Email {
            // PT: Compact constructor: valida antes de expor instância
            // EN: Compact constructor: validates before exposing the instance
            if (valor == null || valor.isBlank()) {
                throw new IllegalArgumentException("PT: email vazio | EN: empty email");
            }
            if (!valor.contains("@")) {
                throw new IllegalArgumentException("PT: email precisa conter @ | EN: email must contain @");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Email("dev@exemplo.org").valor());
    }
}
