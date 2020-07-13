package edu.miu.cs545.group5.onlinemarket.domain;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("buyer")
public class Buyer extends User {
    private int point = 0;

    @ManyToMany
    private List<Seller> followings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="shoppingcart_id")
    private ShoppingCart shoppingCart = new ShoppingCart();

    public Buyer() {
        this.role = Constants.ROLE_BUYER;
    }

    public Buyer(@NotBlank String firstName,
                 @NotBlank String lastName,
                 @NotBlank @Email String email,
                 @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone must match format XXX-XXX-XXXX") String phone,
                 @Past LocalDate birthDate, @Size(min = 6, message = "Password must be 6 characters") String password,
                 @Valid Address address, String role,
                 int active, int point, List<Seller> followings) {
        super(firstName, lastName, email, phone, birthDate, password, address, role, active);
        this.point = point;
        this.followings = followings;
        this.shoppingCart = new ShoppingCart();
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public List<Seller> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Seller> followings) {
        this.followings = followings;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
