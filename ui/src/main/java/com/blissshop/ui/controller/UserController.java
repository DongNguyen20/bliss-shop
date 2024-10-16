package com.blissshop.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserController {
    @GetMapping
    public String goUserManagement() {
        return "user_management";
    }
}
