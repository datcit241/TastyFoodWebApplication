package com.tastyfoodwebapplication.models.products;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DetailedProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(insertable = false, updatable = false, nullable = false)
    private String id;
    @OneToOne
    @JoinColumn(name = "product_category_set_id")
    private ProductCategorySet productCategorySet;
    private String categoryName;
    private double charge;

    public ProductCategorySet getProductCategorySet() {
        return productCategorySet;
    }

    public void setProductCategorySet(ProductCategorySet productCategorySet) {
        this.productCategorySet = productCategorySet;
    }

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
