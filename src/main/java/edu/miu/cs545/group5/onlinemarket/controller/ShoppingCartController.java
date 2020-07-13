package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.*;
import edu.miu.cs545.group5.onlinemarket.service.ProductService;
import edu.miu.cs545.group5.onlinemarket.service.ShoppingCartLineService;
import edu.miu.cs545.group5.onlinemarket.service.ShoppingCartService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartLineService shoppingCartLineService;

    @GetMapping("/cart")
    public String getCart(Model model) {
        User user = userService.getLoggedUser().get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByBuyerId(user.getId());
        model.addAttribute("shoppingCart", shoppingCart);

        return "cart";
    }

    @PostMapping("/cart/items")
    @ResponseBody
    public void addToCart(@RequestBody ShoppingCartLine shoppingCartLine) {
        Product product = productService.findProductById(shoppingCartLine.getProduct().getId());

        User user = userService.getLoggedUser().get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByBuyerId(user.getId());
        List<ShoppingCartLine> shoppingCartLines = shoppingCart.getShoppingCartLines();
        for (ShoppingCartLine line : shoppingCartLines) {
            if (product.getId() == line.getProduct().getId()) {
                line.setQuantity(shoppingCartLine.getQuantity());
                shoppingCartLineService.saveShoppingCartLine(line);
                return;
            }
        }
        shoppingCart.getShoppingCartLines().add(shoppingCartLine);
        shoppingCartService.saveShoppingCart(shoppingCart);
    }

    @PutMapping("/cart/items")
    @ResponseBody
    public void updateToCart(@RequestBody ShoppingCartLine shoppingCartLine) {
        Product product = productService.findProductById(shoppingCartLine.getProduct().getId());

        User user = userService.getLoggedUser().get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByBuyerId(user.getId());
        List<ShoppingCartLine> shoppingCartLines = shoppingCart.getShoppingCartLines();
        for (ShoppingCartLine line : shoppingCartLines) {
            if (product.getId() == line.getProduct().getId()) {
                line.setQuantity(shoppingCartLine.getQuantity());
                shoppingCartLineService.saveShoppingCartLine(line);
            }
        }
    }

    @DeleteMapping("/cart/items/{id}")
    @ResponseBody
    public void removeFromCart(@PathVariable("id") long id) {
        Product product = productService.findProductById(id);

        User user = userService.getLoggedUser().get();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByBuyerId(user.getId());
        List<ShoppingCartLine> shoppingCartLines = shoppingCart.getShoppingCartLines();
        for (ShoppingCartLine line : shoppingCartLines) {
            if (product.getId() == line.getProduct().getId()) {
                shoppingCartLines.remove(line);
                shoppingCart.setShoppingCartLines(shoppingCartLines);
                shoppingCartService.saveShoppingCart(shoppingCart);
            }
        }
    }
}
