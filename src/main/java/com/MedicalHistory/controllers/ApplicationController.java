package com.MedicalHistory.controllers;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.Optional;

@Controller
@RequestMapping("/mh")
public class ApplicationController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    //Login
    @GetMapping("/login")
    public String login(Model model){

        //create user object to hold user form data
        User user=new User();

        model.addAttribute("user",user);
        return "index";
    }

    @PostMapping("/userLogin")
    public String loginUser(@ModelAttribute("user") User user) {
        String email = user.getEmail();
        Optional<User> userdata=userRepo.findByEmail(email);

        if (user.getPassword().equals(userdata.get().getPassword())) {
            return "redirect:/mh/allUsers";
        } else {
            return "error";
        }
    }

//User Register
    @GetMapping("/register")
    public String newUser(Model model){
        UserDto userDto=new UserDto();
        model.addAttribute("userDto",userDto);
         return "UserRegister";
    }
    @ModelAttribute("userDto")
    public UserDto userDto (){
        return new UserDto();
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto){
        System.out.println(userDto);
        userService.createUser(userDto);
        return "redirect:/mh/allUsers";
    }

//show users
@GetMapping("/allUsers")
    public String listUsers(Model model){
        model.addAttribute("listUsers",userService.getAllUsers());
        return "home";
}



}
