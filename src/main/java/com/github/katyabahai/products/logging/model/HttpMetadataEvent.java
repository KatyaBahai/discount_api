package com.github.katyabahai.products.logging.model;

import lombok.Getter;

@Getter
public class HttpMetadataEvent {
    private final String method;
    private final String path;
    private final String contentType;
    private final int status;
    private final long requestDuration;

    public HttpMetadataEvent(String method, String path,
                             String contentType, int status, long requestDuration) {
        this.method = method;
        this.path = path;
        this.contentType = contentType;
        this.status = status;
        this.requestDuration = requestDuration;
    }
}
