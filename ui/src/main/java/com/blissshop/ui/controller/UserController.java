package com.blissshop.ui.controller;

import com.blissshop.ui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping
    public String goUserManagement(Model model) {
        // using load balancer
        String url = "lb://user-service/api/users";
        ResponseEntity<List<User>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("users", response.getBody());
        } else {
            System.out.println("Failed to retrieve users: " + response.getStatusCode());
        }
        return "user_management";
    }
}
