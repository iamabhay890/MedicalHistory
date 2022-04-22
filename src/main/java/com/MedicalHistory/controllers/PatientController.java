package com.MedicalHistory.controllers;


import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepo patientRepo;


    @PostMapping("/")
    public ResponseEntity<PatientDto> createPatientData(@RequestBody PatientDto patientDto) {

        PatientDto createPatientDto = this.patientService.createPatientData(patientDto);
        return new ResponseEntity<>(createPatientDto, HttpStatus.CREATED);
    }




}
