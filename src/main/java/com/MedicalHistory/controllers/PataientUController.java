package com.MedicalHistory.controllers;


import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mh/records")
public class PataientUController {

    @Autowired
    private PatientService patientService;

    @ModelAttribute("patientDto")
    public PatientDto patientDto(){
        return new PatientDto();
    }

    @GetMapping("/addMedicalRecords")
    public String addmedicalRecords(Model model) {
        PatientDto patientDto=new PatientDto();
        model.addAttribute("patientDto",patientDto);

        return "AddMedicalRecords";
    }

    @PostMapping("/addUserMedicalRecords")
    public String adduserMedicalRecords(@ModelAttribute("patientDto") PatientDto patientDto){
        System.out.println(patientDto);
        patientService.createPatientData(patientDto);
        return "Success";
    }

}
