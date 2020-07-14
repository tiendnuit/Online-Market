package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import edu.miu.cs545.group5.onlinemarket.config.ImageUtil;
import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Category;
import edu.miu.cs545.group5.onlinemarket.domain.Product;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.service.CategoryService;
import edu.miu.cs545.group5.onlinemarket.service.ProductService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @ModelAttribute("products")
    public List<Product> products() {

        return productService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAllCategory();
    }

    @GetMapping
    public String shoppage(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        return "shop";
    }

    @GetMapping(value = "/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProductById(id);
        Optional<User> user = userService.getLoggedUser();
        product.getSeller().setFollowingByCurrentUser(false);
        if (user.isPresent()) {
            if (user.get().getRole().equals(Constants.ROLE_BUYER)) {
                Buyer buyer = (Buyer)user.get();
                if (buyer.getFollowings().contains(product.getSeller())) {
                    product.getSeller().setFollowingByCurrentUser(true);
                }

                product.updateCurrentUserReviewIfNeeded(buyer.getId());

            }
        }
        model.addAttribute("product", product);
        model.addAttribute("imgUtil", new ImageUtil());


        return "product_shop_detail";
    }
}
