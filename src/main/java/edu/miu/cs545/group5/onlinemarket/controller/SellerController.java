package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

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
        sellerService.saveUser(seller);
        model.addAttribute("seller", seller);
        redirectAttributes.addFlashAttribute("msg", "Success");
        return "redirect:/seller/sellerForm";
    }
}
