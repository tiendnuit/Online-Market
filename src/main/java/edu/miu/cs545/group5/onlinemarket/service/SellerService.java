package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.dto.SellerResponse;

import java.util.List;
import java.util.Optional;

public interface SellerService {

    List<SellerResponse> getAllSellers();
    public List<SellerResponse> getAllSellersNotApproved();
    public List<SellerResponse> getAllApprovedSellers();
    public Optional<Seller> getSellerById(Long id);
}
