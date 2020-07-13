package edu.miu.cs545.group5.onlinemarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy="shoppingCart")
    private Buyer buyer;

    @OneToMany
    private List<ShoppingCartLine> shoppingCartLines = new ArrayList<>();

    @Transient
    public Double getTotalPrice() {
        double sum = 0D;
        List<ShoppingCartLine> shoppingCartLines = getShoppingCartLines();
        for (ShoppingCartLine sl : shoppingCartLines) {
            sum += sl.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.shoppingCartLines.size();
    }
}
