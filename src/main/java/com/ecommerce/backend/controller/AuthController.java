package com.ecommerce.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.AuthRequest;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    //REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    //LOGIN
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return service.login(request);
    }
}