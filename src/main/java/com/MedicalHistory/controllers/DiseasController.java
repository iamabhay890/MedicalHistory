package com.MedicalHistory.controllers;

import com.MedicalHistory.services.impl.DiseasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/diseas")
public class DiseasController {

    @Autowired
    private DiseasServiceImpl diseasService;

    @PostMapping("/upload")
    public void SaveDiseas(@RequestBody String dName, @RequestBody String activeStatus, @RequestBody MultipartFile file) throws IOException {

        diseasService.saveDiseas(dName,activeStatus,file);
    }

}
