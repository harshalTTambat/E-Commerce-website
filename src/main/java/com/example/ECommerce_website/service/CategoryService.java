package com.example.ECommerce_website.service;

import com.example.ECommerce_website.model.Category;
import com.example.ECommerce_website.model.Product;
import com.example.ECommerce_website.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    // adding new category by admin to database
    public void addCategory(Category category)
    {
        categoryRepository.save(category);
    }

    // Categories section
    // getting all categories from database
    public List<Category> getAllCategory()
    {
        return categoryRepository.findAll();
    }
    public void deleteCatById(int id)
    {
        categoryRepository.deleteById(id);
    }
    public Optional<Category> getCategoryById(int id)
    {
        return categoryRepository.findById(id);
    }

}
