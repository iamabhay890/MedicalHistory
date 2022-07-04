package com.MedicalHistory.services;

import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.payloads.UserDto;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PatientService {

    PatientDto createPatientData(PatientDto patient);

    PatientDto update(PatientDto patient, Integer slipId);

    PatientDto getSlipById(Integer slipId);

    List<PatientDto> getAllSlips();

    void deleteSlip(Integer slipId);

    public List<Patient> getPatients(User user);

}

