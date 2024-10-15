package com.blissshop.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the UI Home Page!");
        model.addAttribute("loggedIn", false);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}