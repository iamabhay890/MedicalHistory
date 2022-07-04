package com.MedicalHistory.controllers;

import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/mh/admin")
public class AdminController {

    static Logger logger = LogManager.getLogger(AdminController.class);


    @Autowired
    private UserService userService;


    //.......show users for admin.........admin controllers start
    @GetMapping("/allUsers")
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userService.getUsers());
        return "Admin/adminHome";
    }


    @GetMapping("/viewAdminProfile/{id}")
    public String viewadminprofile(@PathVariable("id") Integer id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);
        model.addAttribute("userDto", userDto);
        return "Admin/viewAdminProfile";
    }

    //update user for admin
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("userDto", userDto);
        return "Admin/updateUserOnAdminDashboard";
    }

    /**
     * After updating the data through admin home page return to home
     *
     * @return
     */
    @PostMapping("/AfterUpdatingUser")
    public String afterUpdatingUser(@ModelAttribute("userDto") UserDto userDto) {
        LocalDateTime modifiedDate = LocalDateTime.now();
        logger.info("Setting modified date");
        userDto.setModifiedDate(modifiedDate);
        logger.info("Updating the data");
        userService.update(userDto, userDto.getId());
        return "redirect:/mh/index";
    }

    //Will delete user for super admin
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Integer id) {
        this.userService.deleteUser(id);
        return "redirect:/mh/index";
    }


    //----Implementing Soft delete for Admin Dashboard
    @GetMapping("/softDeleteUser/{id}")
    public String softdeleteUser(@PathVariable(value = "id") Integer id) {

        UserDto userDto = userService.getUserById(id);
        System.out.println("Boolean False value is " + Boolean.FALSE);
        userDto.setStatus(Boolean.TRUE);
        System.out.println("status value is :  " + userDto.isStatus());
        userService.createUser(userDto);
        return "redirect:/mh/index";
    }


    @GetMapping("/contactUs/{id}")
    public String Contactus(Model model, @PathVariable("id") Integer id) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("userDto", userDto);
        return "Admin/contactUsForAdmin";
    }
// ............admin controller end.........

}
