package com.MedicalHistory.controllers;
import com.MedicalHistory.Helper.Message;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
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
                    userService.createUser(userDto, file);
                    return "redirect:/mh/";
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
