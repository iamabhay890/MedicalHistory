package com.MedicalHistory.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "patientMedicine")
@Getter
@Setter
@NoArgsConstructor
public class PatientMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pmid;

    private String medicineName;

    private String medicineType;

    private String description;

  /*  @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name="fk_pm",referencedColumnName = "pid")
    private List<Patient> patients; */

}
