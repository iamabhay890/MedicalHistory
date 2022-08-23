package com.MedicalHistory.controllers;

import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.PatientService;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Base64;

@Controller
@RequestMapping("/mh/admin")
public class AdminController {

    static Logger logger = LogManager.getLogger(AdminController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;


    //.......show users for admin.........admin controllers start
    @GetMapping("/allUsers")
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userService.getUsers());
        return "Admin/adminHome";
    }


    @GetMapping("/viewAdminProfile")
    public String viewadminprofile(Principal principal, Model model) {
        User user=userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userDto", user);
        return "Admin/viewAdminProfile";
    }

    //update user for admin
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {
        UserDto userDto=userService.getUserById(id);
        model.addAttribute("userDto", userDto);
        return "Admin/updateUserOnAdminDashboard";
    }

    @PostMapping("/updateAdminProfilePicture")
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
        return "redirect:/mh/index/0";
    }
    //Will delete user for super admin
    @GetMapping("/deleteUser")
    public String deleteUser(@PathVariable(value = "id") Integer id) {
        this.userService.deleteUser(id);
        return "redirect:/mh/index/0";
    }


    //----Implementing Soft delete for Admin Dashboard
    @GetMapping("/softDeleteUser/{id}")
    public String softdeleteUser(@PathVariable(value = "id") Integer id) {

        UserDto userDto = userService.getUserById(id);
        System.out.println("Boolean False value is " + Boolean.FALSE);
        userDto.setStatus(Boolean.TRUE);
        System.out.println("status value is :  " + userDto.isStatus());
        // userService.createUser(userDto);I have to create a single method of soft delete in business class
        return "redirect:/mh/index/0";
    }


    @GetMapping("/contactUs")
    public String Contactus(Model model, Principal principal) {
        User user =userService.findByEmail(principal.getName());
        model.addAttribute("userDto", user);
        return "Admin/contactUsForAdmin";
    }
// ............admin controller end.........

    @GetMapping("/userDetails/{id}")
    public String userDetails(@PathVariable(value = "id") Integer id, Model model,
                              @ModelAttribute("userDto") User user) {
        UserDto userDto = userService.getUserById(id);
        PatientDto patientDto=new PatientDto();
        model.addAttribute("patientDto",patientDto);
        model.addAttribute("userDto", userDto);
        model.addAttribute("listPatients", patientService.getPatients(user));
        return "Admin/userDetails";
    }

    @GetMapping("/inLargeFile/{id}")
    public ResponseEntity<byte[]> inLargeFile(@PathVariable Integer id) {

        Patient patientDto = patientService.getPatinetById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType((patientDto.getReportType())))
                .body(Base64.getDecoder().decode(patientDto.getReport()));
    }

}
