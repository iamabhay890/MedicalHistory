package com.MedicalHistory.controllers;


import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.PatientService;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/mh")
public class LoginController {

    static Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public String home() {
        logger.info("Calling Index page");
        return "Home";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @RequestMapping("/index")
    public String dashboard(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        String role = String.valueOf(userService.loadUserByUsername(principal.getName()).getAuthorities());

        String temp = "[ROLE_ADMIN]";

        if (role.equals(temp)) {
            UserDto userDto = userService.getUserById(user.getId());
            model.addAttribute("listUsers", userService.getUsers());
            model.addAttribute("userDto", userDto);
            return "Admin/adminHome";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("listPatients", patientService.getPatients(user));
            return "User/userHome";
        }
    }

    @GetMapping("/contactUS")
    public String contactusLoginDashboard() {

        return "contactUs";
    }
}