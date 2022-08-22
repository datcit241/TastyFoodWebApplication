package com.tastyfoodwebapplication.models.products;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.*;

@Entity
public class ProductCategorySet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(insertable = false, updatable = false, nullable = false)
    private String id;
    private String name;
    private List<DetailedProductCategory> productCategories;

    public ProductCategorySet() {}

    public ProductCategorySet(String name, List<DetailedProductCategory> productCategories) {
        this.name = name;
        this.productCategories = productCategories;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<DetailedProductCategory> getProductCategories() { return productCategories; }
    public void setProductCategories(List<DetailedProductCategory> productCategories) { this.productCategories = productCategories; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategorySet that = (ProductCategorySet) o;
        return Objects.equals(name, that.name) && Objects.equals(productCategories, that.productCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, productCategories);
    }
}
