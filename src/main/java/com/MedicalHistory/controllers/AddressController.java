package com.MedicalHistory.controllers;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.AddressDto;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.AddressService;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/mh/user/records")
public class AddressController {
    static Logger logger = LogManager.getLogger(AddMedicalRecordController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @ModelAttribute("addressDto")
    public AddressDto addressDto() {

        return new AddressDto();
    }

    @GetMapping("/addAddressRecords")
    public String addAddRecords(Model model, Principal principal) {
        logger.info("Running Add Address records..");
        User user = userService.findByEmail(principal.getName());
        System.out.println("Principal " + principal.getName());
        AddressDto addressDto = new AddressDto();
        UserDto userDto = userService.getUserById(user.getId());
        logger.info("User has logged in through this user id " + userDto.getId());
        model.addAttribute("addressDto", addressDto);
        model.addAttribute("userDto", userDto);
        //it was required for header1 fragment of user block
        model.addAttribute("user", userDto);
        logger.info("passed controller to 'addAddressRecords' url");
        return "User/addAddress";
    }

    @PostMapping("/addUserAddressRecords")
    public String addUserAddRecords(@ModelAttribute("addressDto") AddressDto addressDto,
                                    UserDto userDto) {

        logger.info("Updating user for: " + userDto.getId());
        addressService.update(addressDto, addressDto.getAid());
            return "redirect:/mh/index/0";
    }



    /*@PostMapping("/addUserAddressRecords")
    public String addUserAddRecords(@Valid @ModelAttribute("addressDto") AddressDto addressDto,
                                    BindingResult result,
                                    @ModelAttribute("userDto") User userDto,
                                    Model model) {
        try {
            logger.info("running 'addUserMedicalRecords'  controller");

            logger.info("Id Number " + userDto.getId() + " adding their medical Records");


            //fetching the user by id
            UserDto user = userService.getUserById(userDto.getId());

            if (result.hasErrors()) {
                System.out.println(result);
                logger.info("Add medicine form has some validation error");

                logger.info("Passing User data to show the header content");
                model.addAttribute("user", user);

                model.addAttribute("addressDto", addressDto);
                return "User/addAddress";


            }else
                addressService.createAddressData(addressDto);
            logger.info("Data Has Been Saved");
            logger.info("Passing User data to show the header content");
            model.addAttribute("user", user);
            model.addAttribute("listAddresss", addressService.getUser(userDto));

            return "redirect:/mh/index/0";


        } catch (Exception e) {
            e.printStackTrace();
            return "User/userHome";
        }
    }
*/
}
