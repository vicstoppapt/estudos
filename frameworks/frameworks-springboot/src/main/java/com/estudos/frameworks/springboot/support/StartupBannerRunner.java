package com.estudos.frameworks.springboot.support;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Dicas de endpoints após o arranque (porta padrão 8080).
 */
@Component
public class StartupBannerRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println();
        System.out.println("[frameworks-springboot] API REST (MVC + camadas):");
        System.out.println("  POST http://localhost:8080/api/articles  body: {\"title\":\"...\",\"body\":\"...\"}");
        System.out.println("  GET  http://localhost:8080/api/articles");
        System.out.println("  GET  http://localhost:8080/api/articles/{id}");
        System.out.println();
    }
}
