package com.locato.adservice.services;

import com.locato.adservice.dao.CategoryRepository;
import com.locato.adservice.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
    public Category getCategoryById(String id){
        return categoryRepository.getCategoryById(id);
    }
    public List<Category> getCategories(){return categoryRepository.findAll();}
}
