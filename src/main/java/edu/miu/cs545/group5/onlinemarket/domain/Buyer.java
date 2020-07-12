package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("buyer")
public class Buyer extends User {
    private int point = 0;
    private boolean follow = false;

    @OneToOne
    private ShoppingCart shoppingCart;

    public Buyer( String firstName, String lastName, String email, String phone, LocalDate birthDate, String password, Address address) {
        super(firstName, lastName, email, phone, birthDate, password, address);
    }

    public Buyer() {
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
