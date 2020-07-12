package edu.miu.cs545.group5.onlinemarket.domain;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
