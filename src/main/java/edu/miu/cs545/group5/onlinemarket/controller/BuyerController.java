package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.config.PdfUtil;
import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Order;
import edu.miu.cs545.group5.onlinemarket.service.BuyerService;
import edu.miu.cs545.group5.onlinemarket.service.EmailService;
import edu.miu.cs545.group5.onlinemarket.service.OrderService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/buyer")
public class BuyerController {
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    OrderService orderService;

    @GetMapping(value = {"/followings"})
    public String buyerFollowings(Model model) {
        Buyer currentUser = (Buyer) userService.getLoggedUser().get();
        model.addAttribute("followings", currentUser.getFollowings());
        return "list_followings";
    }

    @PutMapping(value = {"/unfollow/{id}"})
    public @ResponseBody
    String unfollowSeller(@PathVariable("id") Long id, Model model) {
        System.out.println("unfollow: " + id);
        buyerService.unfollowSeller(id);

        return "id = " + id;
    }

    @PutMapping(value = {"/follow/{id}"})
    public @ResponseBody
    String followSeller(@PathVariable("id") Long id, Model model) {
        System.out.println("follow: " + id);
        buyerService.followSeller(id);

        return "id = " + id;
    }

    @GetMapping(value = {"/sendEmail"})
    public String sendEmail() throws IOException, MessagingException {
        Order order = new Order();
        order.setId(112233L);
        emailService.sendPurchaseConfirmation(order);
        return "redirect:/buyer/home";
    }

    @GetMapping(value = {"/orders"})
    public String getOrders(Model model) {
        Buyer currentUser = (Buyer) userService.getLoggedUser().get();
        List<Order> orders = orderService.getOrderHistoryByBuyerId(currentUser.getId());
        model.addAttribute("orders", orders);
        return "list_orders";
    }

    @PostMapping(value={"/review/{id}"})
    public @ResponseBody String reviewProduct(@PathVariable("id") Long id,
                                @RequestParam("message") String message,
                                Model model) {
        buyerService.reviewProduct(id, message);
        return "status: successful";
    }

    @GetMapping(value = {"/order/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> printOrder(@PathVariable("id") Long id) {
        Order order = orderService.getOrderById(id);

        ByteArrayInputStream bis = PdfUtil.orderReport(order);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=order.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
