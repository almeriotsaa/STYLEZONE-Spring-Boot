package com.stylezone.service;

import com.stylezone.model.Product;
import com.stylezone.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Optional<Product> getById(Integer id) {
        return productRepo.findById(id);
    }

    public Product create(Product product) {
        return productRepo.save(product);
    }

    public Product update(Integer id, Product updated) {
        return productRepo.findById(id)
                .map(product -> {
                    product.setCategoryId(updated.getCategoryId());
                    product.setImage(updated.getImage());
                    product.setNameProduct(updated.getNameProduct());
                    product.setDescription(updated.getDescription());
                    product.setPrice(updated.getPrice());
                    product.setStock(updated.getStock());
                    product.setSize(updated.getSize());
                    return productRepo.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(Integer id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(
            String nameProduct,
            Integer categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {

        if (nameProduct != null && categoryId != null) {
            return productRepo
                    .findByNameProductContainingIgnoreCaseAndCategoryId(
                            nameProduct,
                            categoryId
                    );
        }

        if (nameProduct != null) {
            return productRepo.findByNameProductContainingIgnoreCase(nameProduct);
        }

        if (categoryId != null) {
            return productRepo.findByCategoryId(categoryId);
        }

        if (minPrice != null && maxPrice != null) {
            return productRepo.findByPriceBetween(minPrice, maxPrice);
        }

        return productRepo.findAll();
    }
}
