package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Order;
import edu.miu.cs545.group5.onlinemarket.domain.ShoppingCart;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

}
