package com.tastyfoodwebapplication.models.products;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DetailedProductCategory {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String categoryName;
    private double charge;

    public DetailedProductCategory() {}

    public DetailedProductCategory(String id, String categoryName, double charge) {
        this.id = id;
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
        DetailedProductCategory that = (DetailedProductCategory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
