package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.ShoppingCart;
import edu.miu.cs545.group5.onlinemarket.repository.ShoppingCartRepository;
import edu.miu.cs545.group5.onlinemarket.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart getShoppingCartByBuyer(Buyer buyer) {
        return shoppingCartRepository.findByBuyer(buyer).get();
    }

}
