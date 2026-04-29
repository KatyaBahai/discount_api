package com.github.katyabahai.products.logging.listener;

import com.github.katyabahai.products.logging.model.HttpMetadata;
import com.github.katyabahai.products.logging.model.HttpMetadataEvent;
import com.github.katyabahai.products.logging.repository.HttpMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class HttpMetadataLogEventListener {
    private final HttpMetadataRepository httpMetadataRepository;

    @Async
    @EventListener
    public void handle(HttpMetadataEvent event) {
        HttpMetadata httpMetadataLog = HttpMetadata.builder()
                .method(event.getMethod())
                .path(event.getPath())
                .contentType(truncate(event.getContentType()))
                .timestamp(Instant.now())
                .status(event.getStatus())
                .requestDuration(event.getRequestDuration())
                .build();
        httpMetadataRepository.save(httpMetadataLog);
        System.out.println("EVENT RECEIVED BY THE LISTENER");
    }

    private String truncate(String body) {
        if (body == null || body.isBlank()) {
            return "N/A";
        }
        int max = 2000;
        return body.length() > max ? body.substring(0, max) + "..." : body;
    }
}
