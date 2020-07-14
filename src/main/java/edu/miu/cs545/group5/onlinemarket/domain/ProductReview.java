package edu.miu.cs545.group5.onlinemarket.domain;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductReview implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Nullable
    private String message;

    @ManyToOne
    private Product product;

    @OneToOne
    private Buyer buyer;

    public ProductReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
