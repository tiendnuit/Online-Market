package edu.miu.cs545.group5.onlinemarket.domain.dto;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;

public class SellerMapper {

    public static SellerResponse mapToSellerResponse(Seller seller){

        SellerResponse sellerResponse = new SellerResponse();
        sellerResponse.setFirstName(seller.getFirstName());
        sellerResponse.setLastName(seller.getLastName());
        sellerResponse.setEmail(seller.getEmail());
        sellerResponse.setPhone(seller.getPhone());
       // sellerResponse.setAddress(seller.getAddress());
        sellerResponse.setApproved(seller.isApproved());

        return  sellerResponse;

    }
}
