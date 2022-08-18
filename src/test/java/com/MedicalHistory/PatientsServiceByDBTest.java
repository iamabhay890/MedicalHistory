package com.MedicalHistory;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.services.impl.PatientServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.MedicalHistory.repositories.PatientRepo;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PatientsServiceByDBTest {


    @Mock
    PatientRepo patientRepo;

    @InjectMocks
    PatientServiceImpl patientService;


     PatientDto patientDto1 = new PatientDto(21,"COLD","apolo",null,"paracetamol","blood","raj",null,null,null,null,null,null,null);



    @Test
    public void testAdd() {
        String str= "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

    @Test
    public void isPersonExitsById() {

       patientDto1 = patientService.getSlipById(21);
        assertThat(patientDto1);
    }

    @Test(expected = NullPointerException.class)
    @Order(1)
    /*@Rollback()
    @Transactional*/
    @DisplayName("Create Patient Data Test ")
    public void createPatientData()
    {

        patientService.createPatientData(patientDto1);
        Assertions.assertThat(patientDto1.getPId()).isGreaterThan(0);
    }

    @Test(expected = NullPointerException.class)
    @Order(2)
    @Rollback
    @Transactional
    @DisplayName("Update Patients Test")
    public void updatePatientTest(){
        System.out.println("patients DiseaseName "+ patientDto1.getDiseaseName());
        patientDto1.setDiseaseName("Fever");
        patientDto1.setHospitalName("Aiims");
        patientService.update(patientDto1,21);
        Assertions.assertThat(patientDto1.getDiseaseName()).isEqualTo("Fever");
        Assertions.assertThat(patientDto1.getHospitalName()).isEqualTo("Aiims");
        System.out.println("patients DiseaseName "+ patientDto1.getDiseaseName());
    }

    @Test()
    @Order(3)
    @DisplayName("Get SlipById Test ")
    public void getSlipById(){
        patientDto1 = patientService.getSlipById(21);
        Assertions.assertThat(patientDto1.getPId()).isEqualTo(21);
    }

    @Test(expected = NullPointerException.class)
    @Order(5)
    /*@Rollback
    @Transactional*/
    @DisplayName("Delete Slip Test ")
    public void deleteSlip(){
        //System.out.println("patients "+ patientDto1.getPId());
        patientService.deleteSlip(21);
        Assertions.assertThat(patientDto1.getPId()).isNull();
        System.out.println("patients delete "+ patientDto1.getPId());
    }

    @Test(expected = NullPointerException.class)
    @Order(6)
    @DisplayName("Get Patients Test ")
    public void getPatients(){
        patientDto1 = (PatientDto) patientService.getPatients(patientDto1.getUser());
        Assertions.assertThat(patientDto1.getPId()).isEqualTo(21);
    }

    @Test(expected = NullPointerException.class)
    @Order(6)
    @DisplayName("Get Patients Test ")
    public void getAllSlips(){
        patientDto1 = (PatientDto) patientService.getAllSlips();



    }
}
