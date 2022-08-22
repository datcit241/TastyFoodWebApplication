package com.tastyfoodwebapplication.repositories;

import com.tastyfoodwebapplication.models.products.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
}
