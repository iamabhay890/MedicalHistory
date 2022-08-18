package com.MedicalHistory.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pId;


    @Column(name="DiseaseName")
    private String diseaseName;

    @Column(name="HospitalName")
    private String hospitalName;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name="TreatmentDate")
    private String treatmentDate;


    @Column(name = "MedicineName")
    private String medicineName;

    @Column(name = "Report")
    private String report;

    @Column(name="DoctorName")
    private String doctorName;


     @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
     @Column(name="NextAppointment")
     private String nexAppt;

     @Column(name="TypeOfDisease")
     private String typeOfDisease;

    @ManyToOne
    private User user;


}


