package edu.miu.cs545.group5.onlinemarket.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Embeddable
@Getter @Setter
public class Address {
    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @Size(min=2, max=2, message = "State must be 2 characters")
    private String state;

    @Size(min = 5, max = 5, message = "Zip code must be 5 characters")
    private String zipCode;

    public Address() {
    }

    public Address(@NotEmpty String street,
                   @NotEmpty String city,
                   @Size(min = 2, max = 2, message = "State must be 2 characters") String state,
                   @Size(min = 5, max = 5, message = "Zip code must be 5 characters") String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
