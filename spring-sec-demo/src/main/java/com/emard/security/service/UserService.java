package com.emard.security.service;

import com.emard.security.dao.UserRepo;
import com.emard.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    //on peut le faire en bean aussi
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final UserRepo userRepo;
    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
