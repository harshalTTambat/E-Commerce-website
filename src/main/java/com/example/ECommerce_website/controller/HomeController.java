package com.example.ECommerce_website.controller;

import com.example.ECommerce_website.service.CategoryService;
import com.example.ECommerce_website.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/","/home"})
    public String home(Model model)
    {
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model)
    {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }
}
