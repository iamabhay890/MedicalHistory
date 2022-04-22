package com.MedicalHistory.services;

import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.payloads.PatientDto;




public interface PatientService {

    PatientDto createPatientData(PatientDto patient);


}
