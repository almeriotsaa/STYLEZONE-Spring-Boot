package com.stylezone.controller;

import com.stylezone.model.Product;
import com.stylezone.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    // EXISTING: list + filter
    @GetMapping
    public List<Product> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice
    ) {
        return service.search(name, categoryId, minPrice, maxPrice);
    }

    // ✅ NEW: search by name AND categoryId
    @GetMapping("/search")
    public List<Product> searchByNameAndCategory(
            @RequestParam String name,
            @RequestParam Integer categoryId
    ) {
        return service.search(name, categoryId, null, null);
    }

    @GetMapping("/{id}")
    public Product detail(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Product update(
            @PathVariable Integer id,
            @RequestBody Product product
    ) {
        product.setProductId(id);
        return service.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
