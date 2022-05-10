package com.MedicalHistory.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//transfers the data to db
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private int id;
    private String name;

    private String email;
    private String phone;
    private int age;
    private String password;
   // private String address;
    private String gender;
}
