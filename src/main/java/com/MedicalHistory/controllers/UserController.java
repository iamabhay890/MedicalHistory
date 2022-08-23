package com.MedicalHistory.controllers;

import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/mh/user")
public class UserController {

    static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //View user Profile
    @GetMapping("/viewUserProfile")
    public String viewuserProfile(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "User/viewUserProfile";
    }


    //update a single user through user dashboard
    @GetMapping("/showFormForUpdate_s")
    public String showFormForUpdate_s(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("userDto", user);

        //for header1 fragment
        model.addAttribute("user", user);
        return "User/updateSingleUser";
    }

    @PostMapping("/updateUserProfilePicture")
    public String updateUserProfilePicture(@ModelAttribute("user") UserDto userDto,
                                           @RequestParam("profileImage") MultipartFile file) {

        if (file.getOriginalFilename() == null) {
            logger.info("Please select profile picture first");

        } else {
            logger.info("Updating profile picture");
            userService.updateProfilePicture(file, userDto.getId());
        }
        return "redirect:/mh/index/0";
    }

    @PostMapping("/registerUserSingle")
    public String registerUser_s(@ModelAttribute("userDto") UserDto userDto) {
        logger.info("Updating user for: " + userDto.getName());
        userService.update(userDto, userDto.getId());
        return "redirect:/mh/user/viewUserProfile";
    }

    //........Update single user end................


    //Delete self user through user dashboard
    @GetMapping("/deleteSingleUser")
    public String deleteSingleUser(Principal principal) {
        Integer id = userService.findByEmail(principal.getName()).getId();
        this.userService.deleteUser(id);
        return "redirect:/mh/";
    }

    //Contact us in navbar
    @GetMapping("/contactUs")
    public String Contactus(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "User/contactUsForUser";
    }


    @GetMapping("/showFormattedExcelReportPage/{id}")
    public String showFormattedReportPage(Model model, @PathVariable("id") Integer id) {
        UserDto userDto = userService.getUserById(id);
        PatientDto patientDto = new PatientDto();
        model.addAttribute("user",userDto);
        model.addAttribute("patientDto",patientDto);
        return "User/formattedReportExcel";
    }

    @GetMapping("/showFormattedReportPage")
    public String showReportPage(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "User/formattedReport";
    }

    @GetMapping("/showPaymentPage")
    public String showPaymentPage(Principal principal, Model model) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "User/DonateUs";
    }
}

