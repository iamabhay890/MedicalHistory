package com.MedicalHistory.services.impl;

import com.MedicalHistory.entities.Diseas;
import com.MedicalHistory.repositories.DiseasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DiseasServiceImpl {

    @Autowired
    private DiseasRepo diseasRepo;

    public Diseas saveDiseas(String dName, String activeStatus, MultipartFile file) throws IOException {
        Diseas d = new Diseas();
        Integer dId = d.dId;
        Diseas diseas = new Diseas(dId, dName, activeStatus, file.getBytes());
        return diseasRepo.save(diseas);
    }
}
