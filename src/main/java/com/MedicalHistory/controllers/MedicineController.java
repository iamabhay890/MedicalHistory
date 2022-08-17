package com.MedicalHistory.controllers;


import com.MedicalHistory.services.MedicineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class MedicineController {

    private final Logger logger = LogManager.getLogger(MedicineController.class);
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/searchAll")
    public ResponseEntity<?> searchAll() {
        return ResponseEntity.ok(medicineService.allMedicine());
    }
}
