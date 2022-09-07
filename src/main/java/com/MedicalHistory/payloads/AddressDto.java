package com.MedicalHistory.payloads;
import com.MedicalHistory.entities.Address;
import com.MedicalHistory.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddressDto {

    private Integer aid;

    private String address1;

    private String address2;

    private String city;

    private String state;

     private String zip;

    private String country;

    private User user;

}
