package edu.miu.cs545.group5.onlinemarket.service;

import edu.miu.cs545.group5.onlinemarket.domain.Order;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    void sendPurchaseConfirmation(Order order) throws MessagingException, IOException;
}
