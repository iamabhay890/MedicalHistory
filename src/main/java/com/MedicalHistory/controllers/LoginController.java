package com.MedicalHistory.controllers;


import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PatientService;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;


@Controller
@RequestMapping("/mh")
public class LoginController {

    static Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepo patientRepo;


    @GetMapping("/")
    public String home() {
        logger.info("Calling Index page");
        return "Home";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @RequestMapping("/index/{page}")
    public String dashboard(@PathVariable("page") Integer page, Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        String role = String.valueOf(userService.loadUserByUsername(principal.getName()).getAuthorities());

        String temp = "[ROLE_ADMIN]";

        if (role.equals(temp)) {
            UserDto userDto = userService.getUserById(user.getId());
            model.addAttribute("listUsers", userService.getUsers());
            model.addAttribute("userDto", userDto);
            return "Admin/adminHome";
        } else {

            Pageable pageable = PageRequest.of(page, 2);
            Page<Patient> patients = this.patientRepo.findPatientsByUser(user, pageable);

            model.addAttribute("user", user);
            model.addAttribute("listPatients", patients/*patientService.getPatients(user)*/);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", patients.getTotalPages());
            return "User/userHome";
        }
    }

    @GetMapping("/contactUS")
    public String contactusLoginDashboard() {

        return "contactUs";
    }

    @GetMapping("/auth2")
    public String oauth2(Principal principal, OAuth2AuthenticationToken token) {

        String OauthEmail = (String) token.getPrincipal().getAttributes().get("email");
        String newEmail = principal.getName();
        String fullName = (String) token.getPrincipal().getAttributes().get("name");
        
        userService.createOAuth2User(newEmail, fullName, OauthEmail);
        return "redirect:/mh/index/0";
    }
}