package com.estudos.frameworks.quarkus.support;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class StartupBannerObserver {

    void onStart(@Observes StartupEvent event) {
        System.out.println();
        System.out.println("[frameworks-quarkus] API REST (porta 8082):");
        System.out.println("  POST http://localhost:8082/api/articles  body: {\"title\":\"...\",\"body\":\"...\"}");
        System.out.println("  GET  http://localhost:8082/api/articles");
        System.out.println("  GET  http://localhost:8082/api/articles/{id}");
        System.out.println();
    }
}
