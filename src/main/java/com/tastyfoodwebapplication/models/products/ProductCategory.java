package com.tastyfoodwebapplication.models.products;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(insertable = false, updatable = false, nullable = false)
    private String id;
    private String categoryName;
    private double charge;

    public ProductCategory() {}

    public ProductCategory(String categoryName, double charge) {
        this.categoryName = categoryName;
        this.charge = charge;
    }

    public String getId() { return id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public double getCharge() { return charge; }
    public void setCharge(double charge) { this.charge = charge; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
