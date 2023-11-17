package com.locato.adservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationAdRepository extends MongoRepository<AdServiceApplication.Locationad, String> {
}
