package edu.miu.cs545.group5.onlinemarket.domain.dto;

import edu.miu.cs545.group5.onlinemarket.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SellerResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
   // private Address address;
    private boolean isApproved;
}
