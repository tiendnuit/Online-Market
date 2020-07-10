package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @Valid
    private Address shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Buyer buyer;
}
