package com.tastyfoodwebapplication.models.products;

import com.tastyfoodwebapplication.enums.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(insertable = false, updatable = false, nullable = false)
    private String id;
    private String name;
    private double price;
    private double discount;
    private ProductStatus status;
    @OneToMany
    private List<ProductCategory> productCategories;

    public Product() {}

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.price = price;
        this.discount = 0d;
        this.status = ProductStatus.Available;
        this.productCategories = new ArrayList<>();
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }

    public void addCategory(ProductCategory category) { productCategories.add(category); }
    public void removeCategory(ProductCategory category) { productCategories.remove(category); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
