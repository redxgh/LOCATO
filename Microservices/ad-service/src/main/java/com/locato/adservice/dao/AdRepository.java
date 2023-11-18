package com.locato.adservice.dao;

import com.locato.adservice.AdServiceApplication;
import com.locato.adservice.entities.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends MongoRepository<Ad,String> {
}
