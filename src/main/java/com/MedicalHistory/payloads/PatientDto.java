package com.MedicalHistory.payloads;

import com.MedicalHistory.entities.PatientMedicine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PatientDto {

    private Integer pId;

    private String hospitalName;

    private Integer age;

    private String treatmentDate;

    private List<PatientMedicine> medicineName;

    private String description;

    private String report;

    private String doctorName;

    private String nexAppt;

    private String typeOfDisease;
}