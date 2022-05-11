package com.MedicalHistory.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="patient")
@NoArgsConstructor
@Getter
@Setter

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slipId;
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pId;
    @Column(name="HospitalName",nullable = false)
    private String hospitalName;

    @Column(name="Age",nullable = false)
    private Integer age;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name="TreatmentDate",nullable = false)
    private String treatmentDate;


    @Column(name = "MedicineName",nullable = false)
    private String medicineName;

    @OneToMany(targetEntity = PatientMedicine.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "PatientId",referencedColumnName = "pId")
    @Column(name = "MedicineName",nullable = false)
    private List<PatientMedicine> medicineName;


    @Column(name="Description")
    private String description;

    @Column(name = "Report",nullable = false)
    private String report;

    @Column(name="DoctorName",nullable = false)
    private String doctorName;


     @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
     @Column(name="NextAppointment")
     private String nexAppt;

     @Column(name="TypeOfDisease",nullable = false)
     private String typeOfDisease;
}

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name="NextAppointment")
    private String nexAppt;

    @Column(name="TypeOfDisease",nullable = false)
    private String typeOfDisease;
}

