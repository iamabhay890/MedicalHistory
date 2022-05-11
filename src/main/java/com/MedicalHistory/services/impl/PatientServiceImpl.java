package com.MedicalHistory.services.impl;

import com.MedicalHistory.entities.Patient;

import com.MedicalHistory.exceptions.ResourceNotFoundException;
import com.MedicalHistory.payloads.PatientDto;

import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PatientDto update(PatientDto patientDto, Integer slipId) {

        Patient patient =this.patientRepo.findById(slipId).orElseThrow(()-> new ResourceNotFoundException("Patient"," slip ",slipId));
        patient.setHospitalName(patientDto.getHospitalName());
        patient.setAge(patientDto.getAge());
        patient.setTreatmentDate(patientDto.getTreatmentDate());
        patient.setMedicineName(patientDto.getMedicineName());
        patient.setDescription(patientDto.getDescription());
        patient.setReport(patientDto.getReport());
        patient.setDoctorName(patientDto.getDoctorName());
        patient.setNexAppt(patientDto.getNexAppt());
        patient.setTypeOfDisease(patientDto.getTypeOfDisease());


        Patient updatePatient=this.patientRepo.save(patient);
        PatientDto patientDto1=this.patientToDto(updatePatient);
        return patientDto1;

    }

    @Override
    public PatientDto getSlipById(Integer slipId) {
        Patient patient =this.patientRepo.findById(slipId).orElseThrow(()-> new ResourceNotFoundException("Patient"," slip ",slipId));

        return this.patientToDto(patient);

    }

    @Override
    public List<PatientDto> getAllSlips()
    {
        List<Patient> patients=this.patientRepo.findAll();
        List<PatientDto> patientDtos=patients.stream().map(patient -> this.patientToDto(patient)).collect(Collectors.toList());
        return patientDtos;
    }

    @Override
    public void deleteSlip(Integer slipId) {
        Patient patient =this.patientRepo.findById(slipId).orElseThrow(()-> new ResourceNotFoundException("Patient"," slip ",slipId));

      this.patientRepo.delete(patient);

    }


    public Patient dtoToPatient(PatientDto patientDto){

        Patient patient=new Patient();

        patient.setPId(patientDto.getPId());

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

        patientDto.setPId(patient.getPId());
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

