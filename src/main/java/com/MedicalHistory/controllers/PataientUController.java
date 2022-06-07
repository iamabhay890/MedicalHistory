package com.MedicalHistory.controllers;


import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.PatientService;
import com.MedicalHistory.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mh/records")
public class PataientUController {

    static Logger logger= LogManager.getLogger(PataientUController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;


    @ModelAttribute("patientDto")
    public PatientDto patientDto(){

        return new PatientDto();
    }

    @GetMapping("/addMedicalRecords/{id}")
    public String addmedicalRecords(@PathVariable(value = "id") Integer id,Model model) {

        logger.info("Running Add medical records..");

        PatientDto patientDto=new PatientDto();
        UserDto userDto= userService.getUserById(id);

        logger.info("User has logged in through this user id "+userDto.getId());


        model.addAttribute("patientDto",patientDto);
        model.addAttribute("userDto",userDto);

        logger.info("passed controller to 'addUserMedicalRecords' url");

        return "AddMedicalRecords";
    }

    @PostMapping("/addUserMedicalRecords")
    public String adduserMedicalRecords(@ModelAttribute("patientDto") PatientDto patientDto,
                                        @ModelAttribute("userDto") User userDto,
                                        Model model) {

        logger.info("running 'addUserMedicalRecords'  controller");

        logger.info("Id Number "+userDto.getId()+" adding their medical Records");

        patientDto.setUser(userDto);
        System.out.println(patientDto.getUser().getId());

        patientService.createPatientData(patientDto);

        logger.info("Data Has Been Saved");

        logger.info("Passing User data to show the header content");
        UserDto user=userService.getUserById(userDto.getId());
        model.addAttribute("user",user);

        model.addAttribute("listPatients",patientRepo.getPatients(userDto));

        return "userHome";

    }
}
