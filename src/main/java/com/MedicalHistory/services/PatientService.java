package com.MedicalHistory.services;

import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.PatientDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PatientService {

    PatientDto createPatientData(PatientDto patient, MultipartFile file);

    PatientDto update(PatientDto patient, Integer slipId);

    PatientDto getSlipById(Integer slipId);

    List<PatientDto> getAllSlips();

    void deleteSlip(Integer slipId);

    List<Patient> getPatients(User user);

    Patient getPatinetById(Integer id);

}

