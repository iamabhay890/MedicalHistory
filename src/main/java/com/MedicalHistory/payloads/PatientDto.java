package com.MedicalHistory.payloads;

import com.MedicalHistory.entities.PatientMedicine;
import com.MedicalHistory.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import java.util.List;


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


    private String report;

    @NotBlank(message = "Doctor Name can't be null")
    private String doctorName;


    private String nexAppt;


    private String typeOfDisease;

    private byte[] reportFile;


    private User user;

}