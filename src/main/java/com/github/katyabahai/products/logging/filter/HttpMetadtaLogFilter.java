package com.github.katyabahai.products.logging.filter;

import com.github.katyabahai.products.logging.model.HttpMetadataEvent;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class HttpMetadtaLogFilter extends OncePerRequestFilter {
    private final ApplicationEventPublisher publisher;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("FILTER HIT");
        long start = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            try {
                publisher.publishEvent(
                        new HttpMetadataEvent(
                                request.getMethod(),
                                request.getRequestURI(),
                                request.getContentType(),
                                response.getStatus(),
                                duration
                        )
                );
                System.out.println("EVENT PUBLISHED");
            } catch (Exception logEx) {
                logEx.printStackTrace();
            }
        }
    }
}
