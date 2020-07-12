package edu.miu.cs545.group5.onlinemarket.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.dto.SellerResponse;
import edu.miu.cs545.group5.onlinemarket.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({ "/", "/admin" })
public class AdminController {

    @Autowired
    SellerService sellerService;

    @GetMapping
    public String main(Model model) {
        return "admin";
    }

    @GetMapping("{tab}")
    public String tab(@PathVariable String tab) {

        if(tab.equals("tab1")){ return "_tab1";
        } else{ return "_tab2";}
    }


//    @ModelAttribute("unapprovedSellers")
//    public String getAllUnapprovedSellers(Model model){
//        List<SellerResponse> unapprovedSellers = sellerService.getAllSellersByApprovedIsEqualFalse();
//        model.addAttribute("unapprovedSellers", unapprovedSellers);
//
//        return "_tab1";
//    }

    @ModelAttribute("unapprovedSellers")
    List<SellerResponse> unapprovedSellers(Model model) {
        return      Arrays.asList(
                new SellerResponse("Aron", "Teferi", "Aron@yahoo.com", "612335345",   false),
                new SellerResponse("Samuel", "Arefayne", "SelamAron@yahoo.com", "87423535345",   true),
                new SellerResponse("James", "Selam", "Kianemariam@yahoo.com", "12335345",   false),
                new SellerResponse("Aron", "Teferi", "TeferiAron@yahoo.com", "1265423",   true)
        );
    }

        public void ApproveSeller(@RequestParam Long id) {
         Seller approveSeller = sellerService.getSellerById(id).get();
            if(approveSeller.isApproved()==false)
                approveSeller.setApproved(true);
    }

//    public String listUnapprovedSellers(Model model ) {
//        return "_tab1";
//    }

}