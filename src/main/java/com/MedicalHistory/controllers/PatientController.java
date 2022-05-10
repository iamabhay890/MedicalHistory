package com.MedicalHistory.controllers;

import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{slipId}")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto,@PathVariable Integer slipId){

        PatientDto updatePatient = this.patientService.update(patientDto,slipId);
        return ResponseEntity.ok(updatePatient);

    }

    @DeleteMapping("/{slipId}")
    public void deletePatient(@PathVariable Integer slipId){

        this.patientService.deleteSlip(slipId);
    }

    @GetMapping("/{slipId}")
    public ResponseEntity<PatientDto> getSingleSlip(@PathVariable Integer slipId){
        return ResponseEntity.ok(this.patientService.getSlipById(slipId));
    }


    @GetMapping("/")
    public ResponseEntity<List<PatientDto>> getAllSlip(){
        return ResponseEntity.ok(this.patientService.getAllSlips());
    }
}
