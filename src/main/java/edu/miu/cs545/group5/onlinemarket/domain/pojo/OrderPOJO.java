package edu.miu.cs545.group5.onlinemarket.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderPOJO {

    private Long orderId;
    private String productName;
    private Double price;
    private String oderStatus;
    private Long productId;
    private String category;

    public OrderPOJO(Long orderId, String productName, Double price, String oderStatus, Long productId,String category) {
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
        this.oderStatus = oderStatus;
        this.productId = productId;
        this.category = category;
    }
}
