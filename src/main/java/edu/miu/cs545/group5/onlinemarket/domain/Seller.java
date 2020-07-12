package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SELLER")
public class Seller extends User {
    private boolean approved = false;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
