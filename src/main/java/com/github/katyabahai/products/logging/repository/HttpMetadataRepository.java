package com.github.katyabahai.products.logging.repository;

import com.github.katyabahai.products.logging.model.HttpMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HttpMetadataRepository extends MongoRepository<HttpMetadata, String> {

}
