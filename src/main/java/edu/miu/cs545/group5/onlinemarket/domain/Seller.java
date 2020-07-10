package edu.miu.cs545.group5.onlinemarket.domain;

import javax.persistence.Entity;

@Entity
public class Seller extends User {
    private boolean approved = false;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
