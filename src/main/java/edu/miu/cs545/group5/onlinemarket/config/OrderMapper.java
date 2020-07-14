package edu.miu.cs545.group5.onlinemarket.config;

import edu.miu.cs545.group5.onlinemarket.domain.Order;
import edu.miu.cs545.group5.onlinemarket.domain.OrderLine;
import edu.miu.cs545.group5.onlinemarket.domain.pojo.OrderPOJO;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static List<OrderPOJO> mapOder(List<Order> orderList){
        List<OrderPOJO> orderPOJOList = new ArrayList<>();
        for(Order order : orderList){
            for (OrderLine orderLine: order.getOrderLines()){
                orderPOJOList.add(new OrderPOJO(order.getId(),orderLine.getProduct().getName(),
                                                orderLine.getProduct().getPrice(),order.getStatus().toString(),orderLine.getProduct().getId(),
                                                orderLine.getProduct().getCategory().getCategoryName()));
            }
        }

        return orderPOJOList;
    }
}
