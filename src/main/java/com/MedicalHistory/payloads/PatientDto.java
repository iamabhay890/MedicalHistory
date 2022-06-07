package com.MedicalHistory.payloads;

import com.MedicalHistory.entities.PatientMedicine;
import com.MedicalHistory.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class PatientDto {

    private Integer pId;

    private String diseaseName;

    private String hospitalName;


    private String treatmentDate;

    private List<PatientMedicine> medicineName;

    private String report;

    private String doctorName;

    private String nexAppt;

    private String typeOfDisease;

    private byte[] reportFile;

    private User user;

}