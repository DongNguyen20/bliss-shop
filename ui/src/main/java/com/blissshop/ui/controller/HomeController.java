package com.blissshop.ui.controller;

import com.blissshop.ui.model.Category;
import com.blissshop.ui.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Computer", "Description", "/img/category/computer.gif"));
        categories.add(new Category("Pizza", "Description", "/img/category/pizza.gif"));
        categories.add(new Category("Going", "Description", "/img/category/go.gif"));
        categories.add(new Category("Machine", "Description", "/img/category/machine.gif"));

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "Description 1", 20.00, "/img/items/item.jpg"));
        products.add(new Product("Product 2", "Description 2", 30.00, "/img/items/item1.jpg"));
        products.add(new Product("Product 3", "Description 3", 40.00, "/img/items/item2.jpg"));
        products.add(new Product("Product 4", "Description 4", 30.00, "/img/category/go.gif"));

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("loggedIn", true);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}