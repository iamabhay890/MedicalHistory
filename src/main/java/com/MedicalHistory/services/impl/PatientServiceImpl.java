package com.MedicalHistory.services.impl;

import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.exceptions.ResourceNotFoundException;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

    @Override
    public PatientDto createPatientData(PatientDto patientDto, MultipartFile file) {

        Patient patient = this.dtoToPatient(patientDto);
        try {
            patient.setReport(Base64.getEncoder().encodeToString(file.getBytes()));
            patient.setReportName(file.getOriginalFilename());
            patient.setReportType(file.getContentType());
        } catch (Exception e) {
            logger.error("error in setting the file");
            e.printStackTrace();

        }
        Patient savedPatient = this.patientRepo.save(patient);

        return this.patientToDto(savedPatient);
    }

    @Override
    public PatientDto update(PatientDto patientDto, Integer slipId) {

        Patient patient = this.patientRepo.findById(slipId).orElseThrow(() -> new ResourceNotFoundException("Patient", " slip ", slipId));
        patient.setHospitalName(patientDto.getHospitalName());
        patient.setTreatmentDate(patientDto.getTreatmentDate());
        patient.setMedicineName(patientDto.getMedicineName());
        // patient.setReport(patientDto.getReport());
        patient.setDoctorName(patientDto.getDoctorName());
        patient.setNexAppt(patientDto.getNexAppt());
        patient.setTypeOfDisease(patientDto.getTypeOfDisease());


        Patient updatePatient = this.patientRepo.save(patient);
        PatientDto patientDto1 = this.patientToDto(updatePatient);
        return patientDto1;

    }

    @Override
    public PatientDto getSlipById(Integer slipId) {
        Patient patient = this.patientRepo.findById(slipId).orElseThrow(() -> new ResourceNotFoundException("Patient", " slip ", slipId));

        return this.patientToDto(patient);

    }

    @Override
    public Patient getPatinetById(Integer id) {
        return this.patientRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", " slip ", id));
    }


    @Override
    public List<PatientDto> getAllSlips() {
        List<Patient> patients = this.patientRepo.findAll();
        List<PatientDto> patientDtos = patients.stream().map(patient -> this.patientToDto(patient)).collect(Collectors.toList());
        return patientDtos;
    }

    @Override
    public void deleteSlip(Integer slipId) {
        Patient patient = this.patientRepo.findById(slipId).orElseThrow(() -> new ResourceNotFoundException("Patient", " slip ", slipId));

        this.patientRepo.delete(patient);

    }

    @Override
    public List<Patient> getPatients(User user) {
        return this.patientRepo.getPatients(user);
    }


    public Patient dtoToPatient(PatientDto patientDto) {

        Patient patient = new Patient();

        patient.setPId(patientDto.getPId());
        patient.setHospitalName(patientDto.getHospitalName());
        patient.setDiseaseName(patientDto.getDiseaseName());
        patient.setTreatmentDate(patientDto.getTreatmentDate());
        patient.setMedicineName(patientDto.getMedicineName());
        //  patient.setReport(patientDto.getReport());
        patient.setDoctorName(patientDto.getDoctorName());
        patient.setNexAppt(patientDto.getNexAppt());
        patient.setTypeOfDisease(patientDto.getTypeOfDisease());
        patient.setUser(patientDto.getUser());

        return patient;
    }

    public PatientDto patientToDto(Patient patient) {

        PatientDto patientDto = new PatientDto();


        patientDto.setPId(patient.getPId());
        patientDto.setHospitalName(patient.getHospitalName());
        patientDto.setDiseaseName(patient.getDiseaseName());
        patientDto.setTreatmentDate(patient.getTreatmentDate());
        patientDto.setMedicineName(patient.getMedicineName());
        //patientDto.setReport(patient.getReport());
        patientDto.setDoctorName(patient.getDoctorName());
        patientDto.setNexAppt(patient.getNexAppt());
        patientDto.setTypeOfDisease(patient.getTypeOfDisease());
        patientDto.setUser(patient.getUser());

        return patientDto;

    }

}

