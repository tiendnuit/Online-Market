package edu.miu.cs545.group5.onlinemarket.domain;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
//@DiscriminatorValue("buyer")
public class Buyer extends User {
    private int point = 0;

    @OneToOne
    private ShoppingCart shoppingCart;

    public Buyer() {
        this.role = Constants.ROLE_BUYER;
    }
}
