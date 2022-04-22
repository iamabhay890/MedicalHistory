package com.MedicalHistory.services.impl;

import com.MedicalHistory.entities.Patient;

import com.MedicalHistory.payloads.PatientDto;

import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public PatientDto createPatientData(PatientDto patientDto) {

        Patient patient =this.dtoToPatient(patientDto);
        Patient savedPatient=this.patientRepo.save(patient);

        return this.patientToDto(savedPatient);
    }


    public Patient dtoToPatient(PatientDto patientDto){

        Patient patient=new Patient();

        patient.setSlipId(patientDto.getSlipId());
        patient.setHospitalName(patientDto.getHospitalName());
        patient.setAge(patientDto.getAge());
        patient.setTreatmentDate(patientDto.getTreatmentDate());
        patient.setMedicineName(patientDto.getMedicineName());
        patient.setDescription(patientDto.getDescription());
        patient.setReport(patientDto.getReport());
        patient.setDoctorName(patientDto.getDoctorName());
        patient.setNexAppt(patientDto.getNexAppt());
        patient.setTypeOfDisease(patientDto.getTypeOfDisease());

        return patient;
    }

    public PatientDto patientToDto(Patient patient){

        PatientDto patientDto=new PatientDto();

        patientDto.setSlipId(patient.getSlipId());
        patientDto.setHospitalName(patient.getHospitalName());
        patientDto.setAge(patient.getAge());
        patientDto.setTreatmentDate(patient.getTreatmentDate());
        patientDto.setMedicineName(patient.getMedicineName());
        patientDto.setDescription(patient.getDescription());
        patientDto.setReport(patient.getReport());
        patientDto.setDoctorName(patient.getDoctorName());
        patientDto.setNexAppt(patient.getNexAppt());
        patientDto.setTypeOfDisease(patient.getTypeOfDisease());

        return patientDto;

    }
}
