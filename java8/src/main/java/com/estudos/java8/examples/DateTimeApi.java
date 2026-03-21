package com.estudos.java8.examples;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/** java.time. Teoria: README.md. */
public final class DateTimeApi {

    private DateTimeApi() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // Relógio do sistema, sem fuso explícito
        LocalDate hoje = LocalDate.now();
        LocalDateTime agora = LocalDateTime.now();
        System.out.println(hoje + " " + agora);

        // Data/hora com offset/região (DST depende da base de dados da JVM)
        ZonedDateTime sp = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        System.out.println("SP: " + sp);

        // Instant = instante em linha UTC; Duration mede entre dois Instant
        Instant i1 = Instant.now();
        Instant i2 = i1.plusSeconds(30);
        System.out.println("duration: " + Duration.between(i1, i2).getSeconds() + "s");

        // meuPlayground();
    }
}
