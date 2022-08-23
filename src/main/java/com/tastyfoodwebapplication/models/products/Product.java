package com.tastyfoodwebapplication.models.products;

import com.tastyfoodwebapplication.enums.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private double price;
    private double discount;
    private ProductStatus status;
    @OneToMany
    private List<ProductCategorySet> productCategorySets;

    public Product() {}

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.price = price;
        this.discount = 0d;
        this.status = ProductStatus.Available;
        this.productCategorySets = new ArrayList<>();
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

    public List<ProductCategorySet> getProductCategorySets() { return productCategorySets; }
    public void setProductCategorySets(List<ProductCategorySet> productCategorySets) { this.productCategorySets = productCategorySets; }

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
