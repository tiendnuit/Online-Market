package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.*;
import edu.miu.cs545.group5.onlinemarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buyer")
public class CheckoutController {
    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/checkout")
    public String getCheckout(@ModelAttribute("order") Order order, Model model) {
        User user = userService.getLoggedUser().get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByBuyerId(user.getId());
        model.addAttribute("shoppingCart", shoppingCart);

        double total;
        double remainingPoint;
        double usedPoint;
        double totalPrice = shoppingCart.getTotalPrice();
        double point = ((Buyer) user).getPoint();
        if (totalPrice > point) {
            total = totalPrice - point;
            remainingPoint = 0;
            usedPoint = point;
        } else {
            total = 0;
            remainingPoint = point - totalPrice;
            usedPoint = totalPrice;
        }
        model.addAttribute("usedPoint", usedPoint);
        model.addAttribute("total", total);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@ModelAttribute("order") @Valid Order order, BindingResult br, Model model) {
        User user = userService.getLoggedUser().get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByBuyerId(user.getId());

        if (br.hasErrors()) {
            model.addAttribute("shoppingCart", shoppingCart);
            return "checkout";
        }

        double total;
        double remainingPoint;
        double usedPoint;
        double totalPrice = shoppingCart.getTotalPrice();
        double point = ((Buyer) user).getPoint();
        if (totalPrice > point) {
            total = totalPrice - point;
            remainingPoint = 0;
            usedPoint = point;
        } else {
            total = 0;
            remainingPoint = point - totalPrice;
            usedPoint = totalPrice;
        }
        model.addAttribute("usedPoint", usedPoint);
        ((Buyer) user).setPoint(remainingPoint);
        order.setTotal(total);

        order.setBuyer((Buyer) user);
        order.setSeller(getSellerFromShoppingCart(shoppingCart));
        order.setOrderLines(convertOrderLine(shoppingCart.getShoppingCartLines(), order));

        orderService.save(order);

        shoppingCart.getShoppingCartLines().clear();
        shoppingCartService.saveShoppingCart(shoppingCart);

        return "redirect:payment";
    }

    @GetMapping("/payment")
    public String getPayment(@ModelAttribute("payment") Payment payment, Model model) {
        User user = userService.getLoggedUser().get();
        Order order = orderService.getOrderByBuyerId(user.getId());
        model.addAttribute("order", order);

        model.addAttribute("usedPoint", order.getTotalPrice() - order.getTotal());

        return "payment";
    }

    @PostMapping("/payment")
    public String payOrder(@ModelAttribute("payment") @Valid Payment payment, BindingResult br, Model model) {
        User user = userService.getLoggedUser().get();
        Order order = orderService.getOrderByBuyerId(user.getId());

        if (br.hasErrors()) {
            model.addAttribute("order", order);
            return "payment";
        }

        Buyer buyer = (Buyer) user;
        double point = buyer.getPoint();
        double total = order.getTotal();
        point += total * 0.02;
        buyer.setPoint(point);
        model.addAttribute("point", total * 0.02);

        order.setPayment(payment);
        order.setStatus(OrderStatus.PAID);

        payment.setBuyer((Buyer) user);
        paymentService.save(payment);

//        orderService.save(order);

        return "complete";
    }

    public Seller getSellerFromShoppingCart(ShoppingCart shoppingCart) {
        List<ShoppingCartLine> shoppingCartLines = shoppingCart.getShoppingCartLines();
        return shoppingCartLines.get(0).getProduct().getSeller();
    }

    public List<OrderLine> convertOrderLine(List<ShoppingCartLine> shoppingCartLines, Order order) {
        List<OrderLine> orderLines = new ArrayList<>();
        for (ShoppingCartLine line : shoppingCartLines) {
            orderLines.add(new OrderLine(line.getProduct(), line.getQuantity(), order));
        }
        return orderLines;
    }
}
