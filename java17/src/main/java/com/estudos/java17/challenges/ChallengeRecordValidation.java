package com.estudos.java17.challenges;

/** Record com validação. README em challenges/. */
public final class ChallengeRecordValidation {

    private ChallengeRecordValidation() {
    }

    public record Email(String valor) {
        public Email {
            // Compact constructor: valida antes de expor instância
            if (valor == null || valor.isBlank()) {
                throw new IllegalArgumentException("email vazio");
            }
            if (!valor.contains("@")) {
                throw new IllegalArgumentException("email precisa conter @");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Email("dev@exemplo.org").valor());
    }
}
