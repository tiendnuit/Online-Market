package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<OrderLine> items;
}
