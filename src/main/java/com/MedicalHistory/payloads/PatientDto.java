package com.MedicalHistory.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PatientDto {

    private Integer slipId;

    private String hospitalName;

    private Integer age;

    private String treatmentDate;

    private String medicineName;

    private String description;

    private String report;

    private String doctorName;

    private String nexAppt;

    private String typeOfDisease;
}
