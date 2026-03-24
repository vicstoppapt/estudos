package com.estudos.java8.examples;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * PT: java.time. Teoria: README.md.
 * EN: java.time. Theory: README.md.
 */
public final class DateTimeApi {

    private DateTimeApi() {
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        // PT: Relógio do sistema, sem fuso explícito
        // EN: System clock, no explicit time zone
        LocalDate hoje = LocalDate.now();
        LocalDateTime agora = LocalDateTime.now();
        System.out.println(hoje + " " + agora);

        // PT: Data/hora com offset/região (DST depende da base de dados da JVM)
        // EN: Date/time with offset/region (DST depends on the JVM time-zone data)
        ZonedDateTime sp = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        System.out.println("PT: SP: " + sp + " | EN: SP: " + sp);

        // PT: Instant = instante em linha UTC; Duration mede entre dois Instant
        // EN: Instant = point on the UTC timeline; Duration measures between two Instants
        Instant i1 = Instant.now();
        Instant i2 = i1.plusSeconds(30);
        System.out.println("PT: duracao: " + Duration.between(i1, i2).getSeconds() + "s | EN: duration: "
                + Duration.between(i1, i2).getSeconds() + "s");

        // meuPlayground();
    }
}
