package com.tastyfoodwebapplication.models.products;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.*;

@Entity
public class ProductCategorySet {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    @ManyToMany
    private List<DetailedProductCategory> detailedProductCategories;

    public ProductCategorySet() {}

    public ProductCategorySet(String name, List<DetailedProductCategory> detailedProductCategories) {
        this.name = name;
        this.detailedProductCategories = detailedProductCategories;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<DetailedProductCategory> getDetailedProductCategories() { return detailedProductCategories; }
    public void setDetailedProductCategories(List<DetailedProductCategory> detailedProductCategories) { this.detailedProductCategories = detailedProductCategories; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategorySet that = (ProductCategorySet) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
