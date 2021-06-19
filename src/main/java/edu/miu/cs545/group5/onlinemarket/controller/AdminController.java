package edu.miu.cs545.group5.onlinemarket.controller;


import java.util.List;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.ProductReview;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.service.BuyerService;
import edu.miu.cs545.group5.onlinemarket.service.ProductReviewService;
import edu.miu.cs545.group5.onlinemarket.service.SellerService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({ "/admin" })
public class AdminController {

    @Autowired
    SellerService sellerService;

    @Autowired
    UserService userService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    ProductReviewService productReviewService;

    @GetMapping
    public String main(Model model) {
        return "admin";
    }

    @GetMapping("/buyers")
    public String buyers(Model model) {
        List<Buyer> buyers = buyerService.findAllBuyers() ;
        model.addAttribute("buyers", buyers);
        return "buyers-admin";
    }

    @GetMapping("/sellers")
    public String sellers(Model model) {
        List<Seller> sellers = userService.findAllSellers();
        model.addAttribute("sellers", sellers);
        return "sellers-admin";
    }

    @GetMapping("/reviews")
    public String reviews(Model model) {
        List<ProductReview> reviews = productReviewService.getNotApprovedReviews();
        model.addAttribute("reviews", reviews);
        return "reviews-admin";
    }

    @RequestMapping("/enableUser/{userId}")
    public String enableUser(@PathVariable("userId") Long id){
        userService.activateSeller(id);
        return "redirect:/admin/sellers";
    }


    @RequestMapping("/approveUser/{userId}")
    public String approveSeller(@PathVariable("userId") Long id){
            userService.approveSeller(id);
        return "redirect:/admin/sellers";
    }

    @RequestMapping("/approveBuyer/{userId}")
    public String activateBuyer(@PathVariable("userId") Long id){
        userService.activateBuyer(id);
        return "redirect:/admin/buyers";
    }


    @PutMapping(value = {"/reviews/{id}"})
    public String reviews(@PathVariable("id") Long id) {
        productReviewService.approveReview(id);
        return "redirect:/admin/reviews";
    }

}