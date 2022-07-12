package com.MedicalHistory.controllers;


import com.MedicalHistory.entities.Medicine;
import com.MedicalHistory.payloads.MedicineDto;
import com.MedicalHistory.services.MedicineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;


    private final Logger logger = LogManager.getLogger(MedicineController.class);

    @GetMapping("/searchAll")
    public ResponseEntity<?> searchAll(){
        return ResponseEntity.ok(medicineService.allMedicine());
    }

}
