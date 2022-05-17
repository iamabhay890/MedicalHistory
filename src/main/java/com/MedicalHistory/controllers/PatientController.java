package com.MedicalHistory.controllers;

import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/Register")
//@CrossOrigin(origins = "*")
public class PatientController {

    private static final String DIR_TO_UPLOAD = "\\Path\\";


    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepo patientRepo;


    @PostMapping("/patients")

    public ResponseEntity<PatientDto> createPatientData(@RequestBody PatientDto patientDto) {

        PatientDto createPatientDto = this.patientService.createPatientData(patientDto);

        return new ResponseEntity<>(createPatientDto, HttpStatus.CREATED);
    }

    @PutMapping("/{pId}")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto, @PathVariable Integer pId) {

        PatientDto updatePatient = this.patientService.update(patientDto, pId);
        return ResponseEntity.ok(updatePatient);

    }

    @DeleteMapping("/{pId}")
    public void deletePatient(@PathVariable Integer pId) {

        this.patientService.deleteSlip(pId);
    }

    @GetMapping("/{pId}")
    public ResponseEntity<PatientDto> getSingleSlip(@PathVariable Integer pId) {
        return ResponseEntity.ok(this.patientService.getSlipById(pId));
    }


    @GetMapping("/")
    public ResponseEntity<List<PatientDto>> getAllSlip() {
        return ResponseEntity.ok(this.patientService.getAllSlips());
    }
}
