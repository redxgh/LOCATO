package com.locato.adservice.dao;

import com.locato.adservice.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {
    public Category getCategoryById(String id);
}
