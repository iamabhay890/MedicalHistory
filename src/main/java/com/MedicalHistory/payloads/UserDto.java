package com.MedicalHistory.payloads;


import com.MedicalHistory.entities.Address;
import com.MedicalHistory.entities.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;


//transfers the data to db
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
    private Integer id;

    @NotBlank(message = "Please type the First Name")
    @Size(min = 2, max = 20, message = "Your First Name must be between 2 to 20 char")
    private String name;


    private String middleName;

    @NotBlank(message = "Please type the Last Name")
    @Size(min = 2, max = 20, message = "Your Last Name must be between 2 to 20 char")
    private String lastName;

    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Email is not valid !!")
    @NotBlank(message = "Please type email first")
    private String email;

    //@Pattern(regexp = "(^$|[0-9]{10})", message = "Please type valid Phone Number")
    @NotBlank(message = "Phone Number cannot be null")
   /* @Size(min = 14, max = 14, message = "Number must be less or equal to 10 number")*/
    private String phone;

    @Min(value = 0, message = "Age must be equal or greater than 10")
    @Max(value = 100, message = "Age must be less or equal to 120")
    @NotNull(message = "Please type Age their")
    private Integer age;


    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invalid Password,Hover on 'i' for full detail")
    private String password;

    private String confirmPassword;
    private String newPassword;

    private String profilePic;

    private String profilePictureName;


    //@Pattern(regexp="(^$|[0-9]{16})",message = "Please type valid Adhar Number of 16 digit")
    //@Size(min = 19, max = 19, message = "Aadhar must be less or equal to 16 number")
   // @NotNull(message = "Please give Aadhar no")
    private String adharNo;


    @NotBlank(message = "Please select the gender")
    private String gender;


    private Set<Patient> patient;

    private Set<Address>address;

    private boolean status;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String role;

    private boolean passwordsEqual;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    @AssertTrue(message = "Password or confirm Password should be match")
    public boolean isPasswordsEqual()
    {
        return (password==null) ? false : password.equals(confirmPassword);
    }
}


