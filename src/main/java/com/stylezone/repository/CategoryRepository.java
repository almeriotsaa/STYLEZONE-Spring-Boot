package com.stylezone.repository;

import com.stylezone.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByCategoryGenderIgnoreCase(String categoryGender);

    List<Category> findByCategoryTypeIgnoreCase(String categoryType);

    List<Category> findByCategoryGenderIgnoreCaseAndCategoryTypeIgnoreCase(
            String categoryGender,
            String categoryType
    );
}
