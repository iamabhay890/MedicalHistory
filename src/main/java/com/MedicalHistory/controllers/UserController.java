package com.MedicalHistory.controllers;


import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @PostMapping("/")
    //to create user with POST
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

            UserDto createUserDto = this.userService.createUser(userDto);
            return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

   //to update the user with PUT

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updatedUser = this.userService.update(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

    //to delete the user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        System.out.print("Deleted Successfully");
    }

    //to fetch all users

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());

    }

     //to get single user
    @GetMapping("/userId")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));

    }
}
//APIS are tested with postman