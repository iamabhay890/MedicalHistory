package com.login.medical_history_login.controller;

import com.login.medical_history_login.model.User;
import com.login.medical_history_login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/add")
public class AdminController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public String addUser( @RequestBody User user)
    {
        String pwd= user.getPassword();
        String encryptedPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptedPwd);
        userRepository.save(user);
        return "user Added Successfully";

    }


}

