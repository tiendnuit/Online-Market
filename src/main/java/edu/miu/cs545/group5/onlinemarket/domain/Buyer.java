package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Buyer extends User {
    private int point = 0;

    @OneToOne
    private ShoppingCart shoppingCart;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
