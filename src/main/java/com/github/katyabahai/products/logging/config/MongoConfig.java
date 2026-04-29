package com.github.katyabahai.products.logging.config;

import com.github.katyabahai.products.logging.model.HttpMetadata;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig {
    @Bean
    public ApplicationRunner initIndexes(MongoTemplate mongoTemplate) {
        return args -> {
            IndexOperations ops = mongoTemplate.indexOps(HttpMetadata.class);
            ops.ensureIndex(new Index()
                    .on("timestamp", Sort.Direction.ASC)
                    .expire(7, TimeUnit.DAYS));

            ops.ensureIndex(new Index()
                    .on("path", Sort.Direction.ASC)
                    .on("timestamp", Sort.Direction.DESC));

            ops.ensureIndex(new Index()
                    .on("status", Sort.Direction.ASC));
        };
    }
}
