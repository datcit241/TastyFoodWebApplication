package com.tastyfoodwebapplication.models;

import com.tastyfoodwebapplication.models.products.Product;
import com.tastyfoodwebapplication.models.products.DetailedProductCategory;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(insertable = false, updatable = false, nullable = false)
    private String id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    @OneToMany
    private Set<DetailedProductCategory> selectedCategories;

    public CartItem() {}

    public CartItem(Product product, int quantity, Set<DetailedProductCategory> productCategoryList) {
        this.product = product;
        this.quantity = quantity;
        this.selectedCategories = productCategoryList;
    }

    public String getId() { return id; }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void incrementQuantity() { this.quantity++; }

    public Set<DetailedProductCategory> getSelectedCategories() { return selectedCategories; }
    public void setSelectedCategories(Set<DetailedProductCategory> productCategoryList) { this.selectedCategories = productCategoryList; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product) && Objects.equals(selectedCategories, cartItem.selectedCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, selectedCategories);
    }
}
