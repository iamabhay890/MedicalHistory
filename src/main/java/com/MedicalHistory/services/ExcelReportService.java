package com.MedicalHistory.services;

import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelReportService {

    ByteArrayInputStream export(List<Patient> patientDto);

    User getUserById(Integer Id);

    ByteArrayInputStream reportByDate(Integer id);

}

