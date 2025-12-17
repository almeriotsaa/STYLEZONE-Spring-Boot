package com.stylezone.service;

import com.stylezone.model.Product;
import com.stylezone.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Product> search(
            String name,
            Integer categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {

        if (name != null && categoryId != null) {
            return repo.findByNameProductContainingIgnoreCaseAndCategoryId(
                    name,
                    categoryId
            );
        }

        if (name != null) {
            return repo.findByNameProductContainingIgnoreCase(name);
        }

        if (categoryId != null) {
            return repo.findByCategoryId(categoryId);
        }

        if (minPrice != null && maxPrice != null) {
            return repo.findByPriceBetween(minPrice, maxPrice);
        }

        return repo.findAll();
    }
}