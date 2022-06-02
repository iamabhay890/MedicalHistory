package com.MedicalHistory.payloads;


import com.MedicalHistory.entities.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


//transfers the data to db
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Integer id;
    private String name;

    private String email;
    private String phone;
    private int age;
    private String password;
   // private String address;
    private String gender;

}
