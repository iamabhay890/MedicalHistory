package com.MedicalHistory.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="medicine")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mId;

    @Column(name="MedicineName", nullable = false)
    private String medicineName;

    @Column(name="MedicineType", nullable = false)
    private String medicineType;

    @Column(name="Duration", nullable = false)
    private String duration;

    @Column(name="Description", nullable = false)
    private String description;

}