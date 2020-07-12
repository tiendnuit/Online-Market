package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.repository.SellerRepository;
import edu.miu.cs545.group5.onlinemarket.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public User saveUser(Seller seller) {
        return sellerRepository.save(seller);
    }
}
