package com.MedicalHistory.payloads;


import com.MedicalHistory.entities.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;


//transfers the data to db
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Integer id;

    @NotBlank(message = "Please type the name")
    @Size(min = 2,max = 20,message = "Your Full Name must be between 2 to 20 char")
    private String name;

    @Email(regexp ="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",message = "Email is not valid !!")
    @NotBlank(message = "Please type email first")
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Please type valid Phone Number")
    @NotBlank(message = "Phone Number cannot be null")
    private String phone;

    @Min(value = 0,message = "Age must be equal or greater than 10")
    @Max(value = 100,message = "age must be less or equal to 95")
    @NotNull(message = "please type age their")
    private Integer age;



    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message = "Invalid Password,Hover on 'i' for full detail")
    private String password;


    @NotBlank(message = "Please type your address.")
    private String address;


    //@Pattern(regexp="(^$|[0-9]{16})",message = "Please type valid Adhar Number of 16 digit")
    @NotNull(message = "Please give adhar no")
    private Long adharNo;


    @NotBlank(message = "Please select the gender")
    private String gender;


    private Set<Patient> patient;

    private boolean status;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }
}


