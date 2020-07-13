package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Product;
import edu.miu.cs545.group5.onlinemarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

//    @Autowired
//    private ProductService productService;
//
//    @ModelAttribute("products")
//    public List<Product> products() {
//        return productService.
//    }
//
//    @GetMapping
//    public String shoppage() {
//
//    }
}
