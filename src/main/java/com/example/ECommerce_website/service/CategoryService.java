package com.example.ECommerce_website.service;

import com.example.ECommerce_website.model.Category;
import com.example.ECommerce_website.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    // adding new category by admin to database
    public void addCategory(Category category)
    {
        categoryRepository.save(category);
    }

    // getting all categories from database
    public List<Category> getAllCategory()
    {
        return categoryRepository.findAll();
    }
}
