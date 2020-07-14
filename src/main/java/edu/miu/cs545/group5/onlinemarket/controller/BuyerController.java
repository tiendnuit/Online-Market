package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Order;
import edu.miu.cs545.group5.onlinemarket.service.BuyerService;
import edu.miu.cs545.group5.onlinemarket.service.EmailService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class BuyerController {
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    BuyerService buyerService;

    @GetMapping(value = {"buyer/followings"})
    public String buyerFollowings(Model model) {
        Buyer currentUser = (Buyer)userService.getLoggedUser().get();
        model.addAttribute("followings", currentUser.getFollowings());
        return "list_followings";
    }

    @PutMapping(value = {"buyer/unfollow/{id}"})
    public @ResponseBody String unfollowSeller(@PathVariable("id") Long id, Model model) {
        System.out.println("unfollow: "+ id);
        buyerService.unfollowSeller(id);

        return "id = " + id;
    }

    @PutMapping(value = {"buyer/follow/{id}"})
    public @ResponseBody String followSeller(@PathVariable("id") Long id, Model model) {
        System.out.println("follow: "+ id);
        buyerService.followSeller(id);

        return "id = " + id;
    }

    @GetMapping(value = {"buyer/sendEmail"})
    public String sendEmail() throws IOException, MessagingException {
        Order order = new Order();
        order.setId(112233L);
        emailService.sendPurchaseConfirmation(order);
        return "redirect:/buyer/home";
    }
}
