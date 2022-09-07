package com.MedicalHistory.entities;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "address")
public class Address {

    static Logger logger = LogManager.getLogger(User.class);

    @Id
    @Column(name="aid")
    @GeneratedValue(strategy = GenerationType.AUTO) //primary Key
    private Integer aid;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "country")
    private String country;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}
