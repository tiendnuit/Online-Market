package edu.miu.cs545.group5.onlinemarket.domain;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
@DiscriminatorValue("seller")
public class Seller extends User {


    private boolean approved = false;

    public Seller() {
        this.role = Constants.ROLE_SELLER;
    }

    public Seller(@NotBlank String firstName,
                  @NotBlank String lastName,
                  @NotBlank @Email String email,
                  @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone must match format XXX-XXX-XXXX") String phone,
                  @Past LocalDate birthDate,
                  @Size(min = 6, message = "Password must be 6 characters") String password,
                  @Valid Address address, String role,
                  int active, boolean approved) {
        super(firstName, lastName, email, phone, birthDate, password, address, role, active);
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Transient
    private Boolean isFollowingByCurrentUser = false;

    public Boolean getFollowingByCurrentUser() {
        return isFollowingByCurrentUser;
    }

    public void setFollowingByCurrentUser(Boolean followingByCurrentUser) {
        isFollowingByCurrentUser = followingByCurrentUser;
    }
}
