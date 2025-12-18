package com.stylezone.controller;

import com.stylezone.model.Category;
import com.stylezone.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return service.save(category);
    }

    @GetMapping
    public List<Category> list(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String type
    ) {
        return service.search(gender, type);
    }

    @GetMapping("/{id}")
    public Category detail(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Category update(
            @PathVariable Integer id,
            @RequestBody Category category
    ) {
        category.setCategoryId(id);
        return service.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
