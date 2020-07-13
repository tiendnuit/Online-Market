package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Category;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.service.CategoryService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/categoryInputForm")
    public String getCategoryInputForm(@ModelAttribute("category")Category category,Model model){
        Seller seller = (Seller) userService.getLoggedUser().get();
        model.addAttribute("seller", seller);
        return "categoryRegistrationForm";
    }

    @PostMapping("/addCategory")
    public String saveCategory(@Valid Category category, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "categoryRegistrationForm";
        }

        redirectAttributes.addFlashAttribute("msg", "Success");
        categoryService.saveCategory(category);
        Seller seller = (Seller) userService.getLoggedUser().get();
        model.addAttribute("seller", seller);

        model.addAttribute("category", category);
        model.addAttribute("msg", "Success");
        return "redirect:/category/categoryInputForm";
    }
}
