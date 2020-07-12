package edu.miu.cs545.group5.onlinemarket.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@DiscriminatorValue("seller")
public class Seller extends User {


    private boolean approved = false;

    public Seller( String firstName, String lastName, String email, String phone, LocalDate birthDate, String password, Address address) {
        super( firstName, lastName, email, phone, birthDate, password, address);
        this.approved = false;
    }

    public Seller() {
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


}
