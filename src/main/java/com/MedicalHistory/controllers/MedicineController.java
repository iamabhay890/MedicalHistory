package com.MedicalHistory.controllers;

import com.MedicalHistory.payloads.MedicineDto;
import com.MedicalHistory.services.MedicineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
public class MedicineController
{

    private final Logger logger = LogManager.getLogger(MedicineController.class);
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/searchAll")
    public ResponseEntity<?> searchAll() {
        return ResponseEntity.ok(medicineService.allMedicine());
    }


    @PostMapping("/saveData/data")
    public void save(@PathVariable("data") String data){
        MedicineDto medicineDto=new MedicineDto();
        medicineDto.setName(data);
        logger.info("Medicine Saved on Master Medicine");
        medicineService.createMedicineData(medicineDto);
     }
}
