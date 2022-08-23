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
    private String description;
    private String imageURL;
    private double price;
    private double discount;
    private ProductStatus status;
    @ManyToMany
    private List<ProductCategorySet> productCategorySets;

    public Product() {}

    public Product(String name, String description, String imageURL, double price) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.discount = 0d;
        this.status = ProductStatus.Available;
    }

    public Product(String name, String description, String imageURL, double price, List<ProductCategorySet> productCategorySets) {
        this(name, description, imageURL, price);
        this.productCategorySets = productCategorySets;
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }
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
