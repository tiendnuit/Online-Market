package edu.miu.cs545.group5.onlinemarket.domain;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@DiscriminatorValue("buyer")
public class Buyer extends User {
    private int point = 0;
    private boolean follow = false;
    private boolean approved = false;

    @OneToOne
    private ShoppingCart shoppingCart;


    public Buyer() {
        this.role = Constants.ROLE_BUYER;
    }

    public Buyer(@NotBlank String firstName,
                 @NotBlank String lastName,
                 @NotBlank @Email String email,
                 @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone must match format XXX-XXX-XXXX") String phone,
                 @Past LocalDate birthDate, @Size(min = 6, message = "Password must be 6 characters") String password,
                 @Valid Address address, String role,
                 int active, int point, boolean follow) {
        super(firstName, lastName, email, phone, birthDate, password, address, role, active);
        this.point = point;
        this.follow = follow;
        this.shoppingCart = shoppingCart;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
