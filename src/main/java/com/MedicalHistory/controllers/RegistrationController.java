package com.MedicalHistory.controllers;
import com.MedicalHistory.Helper.Message;
import com.MedicalHistory.entities.Address;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.AddressDto;
import com.MedicalHistory.payloads.MedicineDto;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.AddressService;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/mh")
public class RegistrationController {

    static Logger logger = LogManager.getLogger(RegistrationController.class);


    @Autowired
    private UserService userService;

    //Calling User Register
    @GetMapping("/register")
    public String newUser(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        logger.info("Register page is calling through controller");
        return "User/UserRegister";
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result,
                               Model model, HttpSession session,
                               @RequestParam("profileImage") MultipartFile file) {

        logger.info("Running registerUser handler");
        logger.info("Error is there or not " + result.hasErrors());
        logger.info("Error is  " + result);
        try {
            if (result.hasErrors()) {
                logger.warn("UserRegister form has some user input error and running if part of register handler");
                model.addAttribute("useDto", userDto);
                return "User/UserRegister";
            } else {
                logger.info("Executing else part of form validation");
                User userCheck = userService.findByEmail(userDto.getEmail());
                if (userCheck == null) {
                    logger.info("EmailId is unique and not registered with us");
                    System.out.println("Password--> " +userDto.getPassword());
                    System.out.println("Confirm Password--> "+userDto.getConfirmPassword());

                    if(userDto.getPassword().equals(userDto.getConfirmPassword()))
                    {
                        userService.createUser(userDto, file);
                        return "redirect:/mh/";
                    }
                    else{
                        logger.warn("Please Enter Both Password will be same");
                        session.setAttribute("message",
                                new Message("Please Enter Both Password," +
                                        "please Enter Both Password Will be same", "danger"));
                        return "User/UserRegister";
                    }
                } else {
                    logger.warn("This email id is already registered with us");
                    session.setAttribute("message",
                            new Message("This EmailId is already registered with us, " +
                                    "please try with different EmailId", "danger"));
                    return "User/UserRegister";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User/UserRegister";
    }
}
