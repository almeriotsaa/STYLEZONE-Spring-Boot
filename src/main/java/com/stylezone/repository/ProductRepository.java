package com.stylezone.repository;

import com.stylezone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameProductContainingIgnoreCase(String nameProduct);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findByPriceBetween(
            java.math.BigDecimal minPrice,
            java.math.BigDecimal maxPrice
    );

    List<Product> findByNameProductContainingIgnoreCaseAndCategoryId(
            String nameProduct,
            Integer categoryId
    );
}
