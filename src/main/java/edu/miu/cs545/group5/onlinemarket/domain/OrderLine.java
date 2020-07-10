package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderLine implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Order order;
}
