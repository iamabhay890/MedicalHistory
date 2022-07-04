package com.MedicalHistory.controllers;


import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mh/user")
public class UserController {

    static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //View user Profile
    @GetMapping("/viewUserProfile/{id}")
    public String viewuserProfile(@PathVariable(value = "id") Integer id, Model model) {
        UserDto user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "User/viewUserProfile";
    }


    //update a single user through user dashboard
    @GetMapping("/showFormForUpdate_s/{id}")
    public String showFormForUpdate_s(@PathVariable(value = "id") Integer id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("userDto", userDto);

        //for header1 fragment
        model.addAttribute("user", userDto);
        return "User/updateSingleUser";
    }

    @PostMapping("/registerUserSingle")
    public String registerUser_s(@ModelAttribute("userDto") UserDto userDto) {
        logger.info("Updating user for: " + userDto.getName());

        System.out.println("Showing id for single user for update " + userDto.getId());


        userService.update(userDto, userDto.getId());
        return "redirect:/mh/user/viewUserProfile/" + userDto.getId();
    }

    //........Update single user end................


    //Delete self user through user dashboard
    @GetMapping("/deleteSingleUser/{id}")
    public String deleteSingleUser(@PathVariable(value = "id") Integer id) {
        this.userService.deleteUser(id);
        return "redirect:/mh/";
    }


    //Contact us in navbar
    @GetMapping("/contactUs/{id}")
    public String Contactus(Model model, @PathVariable("id") Integer id) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);
        return "User/contactUsForUser";
    }
}

