package com.estudos.frameworks.springboot.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada: arranque do contexto IoC e servidor embutido.
 * {@code scanBasePackages} limita o scan ao prefixo deste módulo (sem classes na “raiz” genérica).
 */
@SpringBootApplication(scanBasePackages = "com.estudos.frameworks.springboot")
public class FrameworksSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworksSpringBootApplication.class, args);
    }
}
