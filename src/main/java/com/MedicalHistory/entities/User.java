package com.MedicalHistory.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //primeryKey
    private Integer id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="email", nullable=false,unique = true)
    private String email;

    @Column(name="phone", nullable=false)
    private String phone;

    @Column(name="age",nullable = false)
    private int age;

    @Column(name="password", nullable=false)
    private String password;

   // @Column(name="address", length = 30)
   // private String address;

    @Column(name="gender",nullable=false)
    private String gender;


}
