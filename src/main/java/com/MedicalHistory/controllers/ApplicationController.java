package com.MedicalHistory.controllers;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mh")
public class ApplicationController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientRepo patientRepo;


    //Login
    @GetMapping("/login")
    public String login(Model model) {

        //create user object to hold user form data
        User user = new User();

        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/userLogin")
    public String loginUser(@ModelAttribute("user") User user) {
        String email = user.getEmail();
        System.out.println(email);
        User userdata = userRepo.findByEmail(email);

        boolean statusLogin=userdata.isStatus();
        if(statusLogin){
            return "error";
        }else {
            if (user.getPassword().equals(userdata.getPassword())) {
                boolean a = user.getEmail().contains("admin");
                if (a) {
                    return "redirect:/mh/allUsers";
                }
                return "redirect:/mh/usersa/" + user.getEmail();
            } else {
                return "error";
            }
        }
    }

    //User Register
    @GetMapping("/register")
    public String newUser(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "UserRegister";
    }

    @ModelAttribute("userDto")
    public UserDto userDto() {
        return new UserDto();
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto) {
        System.out.println(userDto);
        userService.createUser(userDto);
        return "redirect:/mh/login";
    }

    //.......show users for admin.........admin controllers start
    @GetMapping("/allUsers")
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userRepo.getUsers());
        return "adminHome";
    }

    /**
     * show the admin data by clicking on view profile on admin button
     *
     * @param model
     * @return
     */

    @GetMapping("/viewAdminProfile")
    public String viewadminprofile(Model model) {
        User user = userRepo.getAdmin();
        model.addAttribute("user", user);
        return "viewAdminProfile";
    }

    //update user for admin
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("userDto", userDto);
        return "updateUser";
    }

    /**
     * After updating the data through admin home page return to home
     *
     * @return
     */
    @PostMapping("/AfterUpdatingUser")
    public String afterUpdatingUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.createUser(userDto);
        return "redirect:/mh/allUsers";
    }

    //delete user for admin
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Integer id) {
        this.userService.deleteUser(id);
        return "redirect:/mh/allUsers";
    }
// ............admin controller end.........

    @GetMapping("/usersa/{email}")
    public String showSingleUser(@PathVariable(value = "email") String email, Model model) {

        User user = userRepo.findByEmail(email);

        model.addAttribute("user", user);

        model.addAttribute("listPatients", patientRepo.getPatients(user));
        return "userHome";
    }

    //View user Profile
    @GetMapping("/viewUserProfile/{id}")
    public String viewuserProfile(@PathVariable(value = "id") Integer id, Model model) {
        UserDto user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "viewUserProfile";
    }


    //update single user
    @GetMapping("/showFormForUpdate_s/{id}")
    public String showFormForUpdate_s(@PathVariable(value = "id") Integer id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("userDto", userDto);
        return "updateSingleUser";
    }

    @PostMapping("/registerUserSingle")
    public String registerUser_s(@ModelAttribute("userDto") UserDto userDto) {
        System.out.println(userDto);
        userService.createUser(userDto);
        return "redirect:/mh/viewUserProfile/" + userDto.getId();
    }

    //........Update single user end................
    @GetMapping("/deleteSingleUser/{id}")
    public String deleteSingleUser(@PathVariable(value = "id") Integer id) {
        this.userService.deleteUser(id);
        return "redirect:/mh/login";
    }

    //----Implementing Soft delete for Admin Dashboard
    @GetMapping("/softDeleteUser/{id}")
    public String softdeleteUser(@PathVariable(value = "id") Integer id) {

        UserDto userDto = userService.getUserById(id);
        System.out.println("Boolean False value is "+Boolean.FALSE);
        userDto.setStatus(Boolean.TRUE);
        System.out.println("status value is :  "+userDto.isStatus());
        userService.createUser(userDto);
        return "redirect:/mh/allUsers";
    }

}