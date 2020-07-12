package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.service.SellerService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/sellerForm")
    public String getSellerForm(@ModelAttribute("seller") Seller seller,Model model){
        return "sellerRegistrationForm";
    }

    @PostMapping("/addSeller")
    public String saveSeller(@Valid Seller seller, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "sellerRegistrationForm";
        }
        //Seller seller = new Seller();
        seller.setApproved(false);
        userService.save(seller);
        model.addAttribute("seller", seller);
        redirectAttributes.addFlashAttribute("msg", "Success");
        return "redirect:/seller/sellerForm";
    }

    @GetMapping("/sellerDashboard")
    public String getSellerDashBoard(Model model){
        Seller seller = sellerService.findById((long) 1);
          model.addAttribute("seller",seller);

        return "sellerDashboard";
    }

    @GetMapping("/dashboard")
    public String manageSeller(Model model){
        if(sellerService.findById((long) 1) != null)
            model.addAttribute("seller",sellerService.findById((long) 1));
        else {
            model.addAttribute("seller", new Seller());
        }
        return "dashboardHeader";
    }
}
