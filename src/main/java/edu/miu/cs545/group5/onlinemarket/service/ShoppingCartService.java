package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.ShoppingCart;

public interface ShoppingCartService {
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);
    public ShoppingCart getShoppingCartByBuyerId(Long id);
}
