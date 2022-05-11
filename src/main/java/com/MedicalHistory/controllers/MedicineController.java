package com.MedicalHistory.controllers;
import com.MedicalHistory.payloads.MedicineDto;
import com.MedicalHistory.repositories.MedicineRepo;
import com.MedicalHistory.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Register")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;
    @Autowired
    private MedicineRepo medicineRepo;

    @PostMapping("/Medicine")
    public ResponseEntity<MedicineDto> createMedicineData(@RequestBody MedicineDto medicineDto) {

        MedicineDto createMedicineDto = this.medicineService.createMedicineData(medicineDto);
        return new ResponseEntity<>(createMedicineDto, HttpStatus.CREATED);
    }

    @GetMapping("/Medicine/")
    public ResponseEntity<List<MedicineDto>>getAllMedicines(){
        return ResponseEntity.ok(this.medicineService.getAllMedicines());
    }

}