package com.locato.adservice.dao;

import com.locato.adservice.AdServiceApplication;
import com.locato.adservice.entities.Roommatead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface RoommateAdRepository extends MongoRepository<Roommatead, String> {
}
