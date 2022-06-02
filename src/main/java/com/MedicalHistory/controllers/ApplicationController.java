package com.MedicalHistory.controllers;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
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


    //Login
    @GetMapping("/login")
    public String login(Model model){

        //create user object to hold user form data
        User user=new User();

        model.addAttribute("user",user);
        return "index";
    }

    @PostMapping("/userLogin")
    public String loginUser(@ModelAttribute("user") User user) {
        String email = user.getEmail();
        System.out.println(email);
        User userdata=userRepo.findByEmail(email);

        System.out.println(userdata);
       if (user.getPassword().equals(userdata.getPassword())) {
           boolean a=user.getEmail().contains("admin");
           if(a){
               return "redirect:/mh/allUsers";
           }
          return "redirect:/mh/usersa/"+user.getEmail();
       } else {
            return "error";
        }
    }

//User Register
    @GetMapping("/register")
    public String newUser(Model model){
        UserDto userDto=new UserDto();
        model.addAttribute("userDto",userDto);
         return "UserRegister";
    }
    @ModelAttribute("userDto")
    public UserDto userDto (){
        return new UserDto();
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto){
        System.out.println(userDto);
        userService.createUser(userDto);
        return "redirect:/mh/login";
    }

//.......show users for admin.........admin controllers start
@GetMapping("/allUsers")
    public String listUsers(Model model){
        model.addAttribute("listUsers",userRepo.getUsers());
        return "adminHome";
    }

    /**
     * show the admin data by clicking on view profile on admin button
     * @param model
     * @return
     */

    @GetMapping("/viewAdminProfile")
    public String viewadminprofile(Model model){
        User user=userRepo.getAdmin();
        model.addAttribute("user",user);
        return "viewAdminProfile";
    }
//update user for admin
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id,Model model){
        UserDto userDto= userService.getUserById(id);
        model.addAttribute("userDto",userDto);
        return "updateUser";
    }

    /**
     * After updating the data through admin home page return to home
     * @return
     */
    @PostMapping("/AfterUpdatingUser")
    public String afterUpdatingUser(@ModelAttribute("userDto") UserDto userDto){
        userService.createUser(userDto);
        return "redirect:/mh/allUsers";
    }
//delete user for admin
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable (value = "id") Integer id) {
        this.userService.deleteUser(id);
        return "redirect:/mh/allUsers";
    }
// ............admin controller end.........

    @GetMapping("/usersa/{email}")
    public String showSingleUser(@PathVariable(value = "email") String email, Model model){
        User user=userRepo.findByEmail(email);
        model.addAttribute("user",user);
        return "userHome";
    }

//View user Profile
@GetMapping("/viewUserProfile/{id}")
public  String viewuserProfile(@PathVariable(value = "id") Integer id, Model model){
    UserDto user=userService.getUserById(id);
    model.addAttribute("user",user);
    return "viewUserProfile";
}


//update single user
@GetMapping("/showFormForUpdate_s/{id}")
public String showFormForUpdate_s(@PathVariable(value = "id") Integer id,Model model){
    UserDto userDto= userService.getUserById(id);
    model.addAttribute("userDto",userDto);
    return "updateSingleUser";
}
    @PostMapping("/registerUserSingle")
    public String registerUser_s(@ModelAttribute("userDto") UserDto userDto){
        System.out.println(userDto);
        userService.createUser(userDto);
        return "redirect:/mh/viewUserProfile/"+userDto.getId();
    }
//........Update single user end................
@GetMapping("/deleteSingleUser/{id}")
public String deleteSingleUser(@PathVariable (value = "id") Integer id) {
    this.userService.deleteUser(id);
    return "redirect:/mh/login";
}
////
    @GetMapping("/ChangePasswordNew/{id}")
    public String changepasswordnew(@PathVariable(value = "id") Integer id,Model model){
        UserDto userDto= userService.getUserById(id);
        model.addAttribute("userDto",userDto);
        System.out.println("check0");
        return "changePassword";
    }


    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("userDto") UserDto userDto){
        String email=userDto.getEmail();
        System.out.println(email);
        User userdata=userRepo.findByEmail(email);
        System.out.println("check1");
        System.out.println(userdata);
        System.out.println(userDto);
        // User userdata=userRepo.findByEmail(email);
        if(userDto.getPassword().isEmpty()) {
            System.out.println("check2");
            userService.createUser(userDto);
            return "redirect:/mh/login";
        } else {
            System.out.println("check3");
            return "success";
        }
    }
}
