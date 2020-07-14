package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Order;

public interface OrderService {
    void save(Order order);
    Order getOrderByBuyerId(Long id);
}
