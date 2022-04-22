package com.login.medical_history_login.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private int user_id;
    //id,name,email,phone,age,pass,gender
    private String username;
    @Column(unique = true)
    private String email;
    @Size(min = 6, message = "password must be minimum 6 character")
    private String password;
    private int phone;
    private int age;
    private String gender;


}
