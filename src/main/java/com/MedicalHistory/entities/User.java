package com.MedicalHistory.entities;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cfg.annotations.reflection.internal.XMLContext;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {


    static  Logger logger=LogManager.getLogger(User.class);

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


    @Column(name="gender",nullable=false)
    private String gender;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Patient> patient;

    @Column(name = "status")
    //0 for active and 1 for inactive
    private boolean status = Boolean.FALSE;
}
