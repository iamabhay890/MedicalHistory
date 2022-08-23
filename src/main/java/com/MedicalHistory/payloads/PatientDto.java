package com.MedicalHistory.payloads;

import com.MedicalHistory.entities.User;
import lombok.*;

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

    //private byte[] reportFile;

    //private User user;

    private String[] temp;

    private String startDate;
    private String endDate;

    public PatientDto(Integer pId, String diseaseName, String hospitalName, String treatmentDate, String medicineName, String report, String doctorName, String nexAppt, String typeOfDisease, String reportType, User user, String[] temp, String startDate, String endDate) {
        this.pId = pId;
        this.diseaseName = diseaseName;
        this.hospitalName = hospitalName;
        this.treatmentDate = treatmentDate;
        this.medicineName = medicineName;
        this.report = report;
        this.doctorName = doctorName;
        this.nexAppt = nexAppt;
        this.typeOfDisease = typeOfDisease;
        this.reportType = reportType;
        this.user = user;
        this.temp = temp;
        this.startDate = startDate;
        this.endDate = endDate;
    }

   private String report;

    private User user;

    private String reportType;

    private String reportName;

}