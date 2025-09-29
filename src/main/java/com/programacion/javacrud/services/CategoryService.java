package com.programacion.javacrud.services;

import com.programacion.javacrud.dtos.CategoryDTO;
import com.programacion.javacrud.entities.Category;
import com.programacion.javacrud.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // The C (create) in CRUD
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        //requireNonNullOrEmpty(categoryDTO.getName(), "Category name");

        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    // The U (update) in CRUD
    public Category updateCategory(CategoryDTO categoryDTO) {
        if(categoryDTO.getId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        Category category = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryDTO.getId()));

        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }

    // The D (delete) in CRUD
    public void deleteCategory(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        if(!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found with ID: " + id);
        }

        categoryRepository.deleteById(id);
    }
}
