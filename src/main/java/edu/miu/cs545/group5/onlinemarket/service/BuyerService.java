package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;

import java.util.List;

public interface BuyerService {
    void unfollowSeller(Long id);
    void followSeller(Long id);
}
