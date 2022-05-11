package com.MedicalHistory.services;

import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.payloads.PatientDto;

import java.util.List;


public interface PatientService {

    PatientDto createPatientData(PatientDto patient);

    PatientDto update(PatientDto patient, Integer slipId);

    PatientDto getSlipById(Integer slipId);

    List<PatientDto> getAllSlips();

    void deleteSlip(Integer slipId);

}