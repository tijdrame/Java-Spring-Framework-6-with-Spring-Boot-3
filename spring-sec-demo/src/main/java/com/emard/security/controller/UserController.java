package com.emard.security.controller;

import com.emard.security.model.User;
import com.emard.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService service;
    @PostMapping("register")
    public User register(@RequestBody User user){
        return service.saveUser(user);
    }
}
