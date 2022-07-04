package com.MedicalHistory.payloads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MedicineDto {

    private Integer mId;

    private String name;

    private String duration;

    private String description;

}