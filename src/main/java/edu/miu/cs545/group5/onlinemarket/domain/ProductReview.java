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
    private String description;

    @ManyToOne
    private Product product;



}
