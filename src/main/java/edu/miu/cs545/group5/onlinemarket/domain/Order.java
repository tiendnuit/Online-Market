package edu.miu.cs545.group5.onlinemarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @Valid
    private Address shippingAddress;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private List<OrderLine> orderLines = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Transient
    public Double getTotalPrice() {
        double sum = 0D;
        List<OrderLine> orderLines = getOrderLines();
        for (OrderLine ol : orderLines) {
            sum += ol.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.orderLines.size();
    }
}
