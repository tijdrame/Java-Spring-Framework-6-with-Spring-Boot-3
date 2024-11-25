package com.emard.security.service;

import com.emard.security.dao.UserRepo;
import com.emard.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;
    public User saveUser(User user){
        return userRepo.save(user);
    }
}
