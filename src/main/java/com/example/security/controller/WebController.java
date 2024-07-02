package com.example.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {


    @GetMapping("/")
    public String getHome() {
        return "index";
    }


    @GetMapping("/user")

    public String getUser() {
        return "user";
    }

    @GetMapping("/admin")

    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/login")

    public String getLogin() {
        return "login";
    }

}
