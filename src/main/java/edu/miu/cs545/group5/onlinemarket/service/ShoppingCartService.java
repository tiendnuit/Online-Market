package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.ShoppingCart;

public interface ShoppingCartService {
    public ShoppingCart getShoppingCartByBuyer(Buyer buyer);
}
