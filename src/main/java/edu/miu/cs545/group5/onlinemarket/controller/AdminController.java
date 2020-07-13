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




    public List<? extends User> notActiveUsers(Model model) {
        return userService.getNotApprovedUsers();

    }
         public void updateUser(Long id){

        User user = userService.getById(id).get();

        if(user.getRole().toUpperCase().equals("SELLER")){
            ((Seller)user).setApproved(true);

        }
        if(user.getRole().toUpperCase().equals(("BUYER"))){
            ((Buyer)user).setApproved(true);

        }
        userService.save(user);
    }


        @RequestMapping("/enableUser/{userId}")
        public String enableUser(@PathVariable("userId") Long id){
            userService.enableUser(id);
            return "redirect:/admin/#tab1";
            //return "_tab2";
        }
//    @RequestMapping("/allUsers")
//        public String getAllUsersExceptAdmin(Model model){
//            List<User> users = userService.findAllUserSellersAndBuyers();
//            model.addAttribute("users", users);
//            return "_tab1";
//    }


    @RequestMapping("/approveUser/{userId}")
    public String approveSeller(@PathVariable("userId") Long id, Model model){
            userService.approveSeller(id);
        return "admin";
    }



}