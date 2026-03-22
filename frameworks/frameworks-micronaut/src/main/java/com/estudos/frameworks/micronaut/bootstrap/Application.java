package com.estudos.frameworks.micronaut.bootstrap;

import io.micronaut.runtime.Micronaut;

/**
 * Ponto de entrada Micronaut (Netty). Beans sob {@code com.estudos.frameworks.micronaut}.
 */
public final class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
