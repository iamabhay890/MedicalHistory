package com.MedicalHistory.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //primeryKey
    private int id;

    @Column(name="name", nullable=false,length = 30)
    private String name;

    @Column(name="email", nullable=false,length = 30,unique = true)
    private String email;

    @Column(name="phone", nullable=false,length = 15)
    private String phone;

    @Column(name="age",nullable = false,length = 3)
    private int age;

    @Column(name="password", nullable=false,length = 30)
    private String password;

   // @Column(name="address", length = 30)
   // private String address;

    @Column(name="gender",nullable=false,length = 10)
    private String gender;


}
