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
public class CheckoutController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartLineService shoppingCartLineService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String getCheckout(@ModelAttribute("order") Order order, Model model) {
        User user = userService.getLoggedUser().get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByBuyerId(user.getId());
        model.addAttribute("shoppingCart", shoppingCart);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@ModelAttribute("order") @Valid Order order, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "checkout";
        }

        User user = userService.getLoggedUser().get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByBuyerId(user.getId());

        order.setBuyer((Buyer) user);
        order.setOrderLines(convertOrderLine(shoppingCart.getShoppingCartLines()));

        orderService.save(order);

        return "complete";
    }

    public List<OrderLine> convertOrderLine(List<ShoppingCartLine> shoppingCartLines){
        List<OrderLine> orderLines = new ArrayList<>();
        for(ShoppingCartLine line : shoppingCartLines){
            orderLines.add(new OrderLine(line.getProduct(), line.getQuantity()));
        }
        return orderLines;
    }
}
