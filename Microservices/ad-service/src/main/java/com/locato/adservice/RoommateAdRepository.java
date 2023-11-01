package com.locato.adservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "ads", path = "ads")
public interface RoommateAdRepository extends MongoRepository<AdServiceApplication.Roommatead, String> {
}
