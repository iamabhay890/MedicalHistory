package com.MedicalHistory.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="patient")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pId;


    @Column(name="DiseaseName",nullable = false)
    private String diseaseName;

    @Column(name="HospitalName",nullable = false)
    private String hospitalName;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name="TreatmentDate",nullable = false)
    private String treatmentDate;

//    @OneToMany(targetEntity = PatientMedicine.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "PatientId",referencedColumnName = "pId")
//    @Column(name = "MedicineName")
//    private List<PatientMedicine> medicineName;

    @Column(name = "Report",nullable = false)
    private String report;

    @Column(name="DoctorName")
    private String doctorName;


     @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
     @Column(name="NextAppointment")
     private String nexAppt;

     @Column(name="TypeOfDisease",nullable = false)
     private String typeOfDisease;

    @ManyToOne
    private User user;

}


