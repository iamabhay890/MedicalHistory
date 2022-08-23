package com.MedicalHistory.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "diseas")
public class Diseas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer dId;

    @Column
    public String dName;

    @Column
    public String activeStatus;

    @Lob
    private byte[] reportFile;


}
