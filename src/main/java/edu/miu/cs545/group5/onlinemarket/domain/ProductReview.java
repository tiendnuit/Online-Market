package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductReview implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

}
