package com.MedicalHistory.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mh")
public class PasswordController {
    static Logger logger= LogManager.getLogger(PasswordController.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;


    //Change Password Process
    @GetMapping("/ChangePassword/{id}")
    public String Change(@PathVariable(value = "id") Integer id, Model model) {
        logger.info("Running Password Change..");
        UserDto userDto = userService.getUserById(id);
        logger.info("User has logged in through this user id "+userDto.getId());
        model.addAttribute("userDto", userDto);
        return "Password/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("userDto") UserDto userDto) {

        String oldPassword = userDto.getPassword();
        String newPassword = userDto.getNewPassword();
        logger.info("Enter Old Password -> " + oldPassword + "  | "
                +" Enter New Password -> " +newPassword );
        UserDto userDto1 = userService.getUserById(userDto.getId());
        if(bCryptPasswordEncoder.matches(oldPassword, userDto1.getPassword()))
        {
            logger.info("Running Change Password Process..");
            if(!oldPassword.equals(newPassword)) {
                userDto.setPassword(bCryptPasswordEncoder.encode(newPassword));
                userService.updatePassword(userDto);
                logger.info("Successfully Change New Password..");
                return "redirect:/mh/index";

            } else {
                logger.warn("Please Not enter old password");
                return "redirect:/mh/ChangePassword/"+userDto.getId();
            }
        }

        else {
            logger.warn("Some Information are not Correct !");
            return "redirect:/mh/ChangePassword/"+userDto.getId();
        }
    }



    //forgot Password Process
    @GetMapping("/forgotPassword")
    public String forgot(Model model)
    {
        logger.info("Running User Forgot Password..");
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "Password/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassword(@ModelAttribute("user") UserDto user) {

        String email = user.getEmail();
        String phone = user.getPhone();
        User userdata = userService.findByEmail(email);
        String oldPassword = userdata.getPassword();
        String password = user.getNewPassword();
        logger.info("User Old password in Database -> " + oldPassword + "  | "
                +" Enter password by user in UI --> " +password );

            logger.info("Check email id is available in the database");
        if (phone.equals(userdata.getPhone()))
        {
            logger.info("Running Update Password Process..");
            if(!bCryptPasswordEncoder.matches(password,oldPassword))
            {
                user.setPassword(bCryptPasswordEncoder.encode(password));
                userService.forgotPass(user);
                logger.info("Successfully Updated New Password..");
                return "redirect:/mh/login";
            }else{
                logger.warn("Some information are not Correct");
                return "redirect:/mh/forgotPassword";
            }
        }
        else {
            logger.warn("some information are not valid");
            return "redirect:/mh/forgotPassword";
        }
    }
}