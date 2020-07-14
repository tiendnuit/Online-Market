package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Order;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;


public interface OrderService {
    void save(Order order);
    Order getOrderByBuyerId(Long id);

    public Page<Order> findPageableOrderBySellerId(Pageable pageable, Long id);
    public void cancelOrderById(Long orderId);
    public int updateOrderStatusBuOrderId(Long orderId, String status);
}
