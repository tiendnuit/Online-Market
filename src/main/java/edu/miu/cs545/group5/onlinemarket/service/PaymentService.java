package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Payment;

public interface PaymentService {
    Payment save(Payment payment);
    Payment getPaymentByBuyerId(Long id);
}
