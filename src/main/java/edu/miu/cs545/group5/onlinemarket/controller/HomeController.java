package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.config.Constants;
import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Order;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.service.BuyerService;
import edu.miu.cs545.group5.onlinemarket.service.EmailService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    EmailService emailService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    UserService userService;

    private Boolean hasRole(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role))) {
            return true;
        }
        return false;
    }
    @GetMapping(value = {"/","/home"})
    public String home() {
        if (hasRole(Constants.ROLE_SELLER)) {
            return "redirect:/seller/sellerDashboard";
        } else if (hasRole(Constants.ROLE_BUYER)) {
            return "redirect:/buyer/home";
        } else if (hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/admin";
        }

        return "redirect:/login";
    }

    @GetMapping(value = {"buyer/home"})
    public String buyerHome() {
        return "buyer_home";
    }

    @GetMapping(value = {"buyer/sendEmail"})
    public String sendEmail() throws IOException, MessagingException {
        Order order = new Order();
        order.setId(112233L);
        emailService.sendPurchaseConfirmation(order);
        return "redirect:/buyer/home";
    }

    ///
    @GetMapping(value = {"buyer/followings"})
    public String buyerFollowings(Model model) {
        Buyer currentUser = (Buyer)userService.getLoggedUser().get();
        model.addAttribute("followings", currentUser.getFollowings());
        return "list_followings";
    }

}
