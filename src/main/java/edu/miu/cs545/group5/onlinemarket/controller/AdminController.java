package edu.miu.cs545.group5.onlinemarket.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.domain.dto.SellerResponse;
import edu.miu.cs545.group5.onlinemarket.service.BuyerService;
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

    @GetMapping
    public String main(Model model) {
        List<Buyer> buyers = buyerService.findAllBuyers() ;
        model.addAttribute("buyers", buyers);
        return "admin";
    }

    @GetMapping("{tab}")
    public String tab(@PathVariable String tab,Model model) {

        if(tab.equals("tab1")){
            List<Buyer> buyers = buyerService.findAllBuyers() ;
            model.addAttribute("buyers", buyers);
            return "_tab1";
        } else{
            List<Seller> sellers = userService.findAllSellers();
            model.addAttribute("sellers", sellers);
            return "_tab2";
        }
    }
        @RequestMapping("/enableUser/{userId}")
        public String enableUser(@PathVariable("userId") Long id){
            userService.activateSeller(id);
            return "admin";
            //return "_tab2";
        }


    @RequestMapping("/approveUser/{userId}")
    public String approveSeller(@PathVariable("userId") Long id){
            userService.approveSeller(id);
        return "admin";
    }

    @RequestMapping("/approveBuyer/{userId}")
    public String activateBuyer(@PathVariable("userId") Long id){
        userService.activateBuyer(id);
        return "admin";
    }




}