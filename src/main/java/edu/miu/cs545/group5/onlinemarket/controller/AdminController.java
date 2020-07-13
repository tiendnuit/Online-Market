package edu.miu.cs545.group5.onlinemarket.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import edu.miu.cs545.group5.onlinemarket.domain.Buyer;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.domain.dto.SellerResponse;
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

    @GetMapping
    public String main(Model model) {
        return "admin";
    }

    @GetMapping("{tab}")
    public String tab(@PathVariable String tab) {

        if(tab.equals("tab1")){ return "_tab1";
        } else{ return "_tab2";}
    }


    @ModelAttribute("notApprovedUsers")
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

    @ModelAttribute("users")
    //@RequestMapping("/allUsers")
    public List<User> getAllUsersExceptAdmin(Model model){
        List<User> users = userService.findAllUserSellersAndBuyers();
        //model.addAttribute("users", users);
        return users;
    }

}