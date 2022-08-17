package com.MedicalHistory.payloads;

import com.MedicalHistory.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@Getter
@Setter
public class PatientDto {

    private Integer pId;

    @NotBlank(message = "Disease Name can't be blank")
    private String diseaseName;

    @NotBlank(message = "Hospital Name can't be null")
    private String hospitalName;


    private String treatmentDate;

    private String medicineName;

    @NotBlank(message = "Doctor Name can't be null")
    private String doctorName;


    private String nexAppt;


    private String typeOfDisease;

    private String report;

    private User user;

    private String reportType;

    private String reportName;

}