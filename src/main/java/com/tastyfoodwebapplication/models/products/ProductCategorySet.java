package com.tastyfoodwebapplication.models.products;

import org.hibernate.annotations.Type;

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

    public ProductCategorySet() {}

    public ProductCategorySet(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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
