package com.example.security.controller;

import com.example.security.security.IAuthenticationFacade;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {
    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;

    @GetMapping("/dashboard")
    public String getDashboard(){
        return "Dashboard Page";
    }
    @GetMapping("/api/admin/users")
    public String getUsers() {
        return "List user page";
    }

    @GetMapping("/api/categories")
    public String getCategory() {
        return "Categorys Page";
    }

    @GetMapping("/api/products")
    public String getProduct() {
        return "Products Page";
    }

    @GetMapping("/api/brands")
    public String getBrand() {
        return "Brands Page";
    }

    @GetMapping("/api/orders")
    public String getOrder() {
        return "Orders Page";
    }

    @GetMapping("/api/posts")
    public String getPost() {
        return "Posts Page";
    }

    @GetMapping("/api/user")
    public String userInformation() {
        return "User Information";
    }
}
