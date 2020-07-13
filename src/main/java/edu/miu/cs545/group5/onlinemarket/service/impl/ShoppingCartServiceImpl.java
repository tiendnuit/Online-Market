package edu.miu.cs545.group5.onlinemarket.service.impl;

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
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getShoppingCartByBuyerId(Long id) {
        return shoppingCartRepository.findByBuyerId(id).get();
    }

}
