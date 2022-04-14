package com.MedicalHistory.controllers;


import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }

}
