package com.stylezone.service;

import com.stylezone.model.Category;
import com.stylezone.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public Category save(Category category) {
        return repo.save(category);
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Category findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Category> search(String gender, String type) {

        if (gender != null && type != null) {
            return repo.findByCategoryGenderIgnoreCaseAndCategoryTypeIgnoreCase(
                    gender, type
            );
        }

        if (gender != null) {
            return repo.findByCategoryGenderIgnoreCase(gender);
        }

        if (type != null) {
            return repo.findByCategoryTypeIgnoreCase(type);
        }

        return repo.findAll();
    }
}
