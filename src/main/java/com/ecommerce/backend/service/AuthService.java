package com.ecommerce.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.config.JwtUtil;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepo,
                       BCryptPasswordEncoder encoder,
                       JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    //Register
    public String register(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // 🔐 IMPORTANT
        user.setRole("ROLE_USER");
        userRepo.save(user);
        return "User Registered Successfully";
    }

    //Login
    public String login(String email, String password) {

    User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    System.out.println("Entered Password: " + password);
    System.out.println("DB Password: " + user.getPassword());

    if (!encoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid Password");
    }

    return jwtUtil.generateToken(email);
}
}