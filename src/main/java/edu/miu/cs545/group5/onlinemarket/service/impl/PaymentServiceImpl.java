package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.Payment;
import edu.miu.cs545.group5.onlinemarket.repository.PaymentRepository;
import edu.miu.cs545.group5.onlinemarket.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByBuyerId(Long id) {
        return paymentRepository.findByBuyerId(id).orElse(null);
    }
}
