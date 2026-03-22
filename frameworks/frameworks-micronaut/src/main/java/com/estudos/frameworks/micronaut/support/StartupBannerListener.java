package com.estudos.frameworks.micronaut.support;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Singleton;

@Singleton
public class StartupBannerListener implements ApplicationEventListener<StartupEvent> {

    @Override
    public void onApplicationEvent(StartupEvent event) {
        System.out.println();
        System.out.println("[frameworks-micronaut] API REST (porta 8081):");
        System.out.println("  POST http://localhost:8081/api/articles  body: {\"title\":\"...\",\"body\":\"...\"}");
        System.out.println("  GET  http://localhost:8081/api/articles");
        System.out.println("  GET  http://localhost:8081/api/articles/{id}");
        System.out.println();
    }
}
