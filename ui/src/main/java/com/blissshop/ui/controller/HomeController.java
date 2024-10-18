package com.blissshop.ui.controller;

import com.blissshop.ui.model.Category;
import com.blissshop.ui.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Value("${server.servlet.context-path}")
    private String staticResourcePath;

    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Computer", "Description", setValidPathResource("/img/category/computer.gif")));
        categories.add(new Category("Pizza", "Description", setValidPathResource("/img/category/pizza.gif")));
        categories.add(new Category("Going", "Description", setValidPathResource("/img/category/go.gif")));
        categories.add(new Category("Machine", "Description", setValidPathResource("/img/category/machine.gif")));

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "Description 1", 20.00, setValidPathResource("/img/items/item.jpg")));
        products.add(new Product("Product 2", "Description 2", 30.00, setValidPathResource("/img/items/item1.jpg")));
        products.add(new Product("Product 3", "Description 3", 40.00, setValidPathResource("/img/items/item2.jpg")));
        products.add(new Product("Product 4", "Description 4", 30.00, setValidPathResource("/img/category/go.gif")));

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("loggedIn", true);

        //set image for css background-image
        model.addAttribute("avatarImage", setValidPathResource("/img/user.avif"));
        //using variablPath
        model.addAttribute("variablePath", setValidPathResource("/img/category/computer.gif"));
        //using externalPath
        model.addAttribute("externalImage", "test.jpg");
        return "home";
    }

    @GetMapping("/login")
    public String goLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (authenticateUser(username, password)) {
            return "redirect:/";
        } else {
            model.addAttribute("message", "Invalid username or password");
            return "login";
        }
    }

    private boolean authenticateUser(String username, String password) {
        return "admin".equals(username) && "12345".equals(password);
    }

    public String setValidPathResource(String filePath) {
        return staticResourcePath + filePath;
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}