package com.github.katyabahai.products.logging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "http_logs")
public class HttpMetadata {
    @Id
    private String id;
    private String method;
    private String path;
    private final String contentType;
    private int status;
    private Instant timestamp;
    private long requestDuration;
}
