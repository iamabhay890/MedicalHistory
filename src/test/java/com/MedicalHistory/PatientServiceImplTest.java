package com.MedicalHistory;
import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.PatientDto;
import com.MedicalHistory.services.impl.PatientServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.MedicalHistory.repositories.PatientRepo;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceImplTest {


    @MockBean
    PatientRepo patientRepo;

    @Autowired
    PatientServiceImpl patientService;


   PatientDto patientDto = new PatientDto(1, "COLD", "apolo", null, "paracetamol", "blood", "raj", null, null, null, null, null, null, null);
   Patient  patient = new Patient(2, "Disease1", "aiims", null, "crocin", "blood", "amit", null, null, null,null,null);



    @Test
    @DisplayName("Create Patient Data Test ")
    public void createPatientData()
    {        MultipartFile file = new MockMultipartFile(patientDto.getReport(),patientDto.getReport().getBytes());

        when(patientRepo.save(any())).thenReturn(patientService.dtoToPatient(patientDto));
        Assert.assertEquals(patientDto.getPId(), patientService.createPatientData(patientDto,file).getPId());
        System.out.println("check Create Patients "+ patientService.createPatientData(patientDto,file).getPId());
    }

    @Test
    @DisplayName("Update Patients Test")
    public void updatePatientTest(){
        System.out.println("patients DiseaseName "+ patient.getDiseaseName());
        patient.setDiseaseName("Fever");
        when(patientRepo.save(any())).thenReturn(patientService.patientToDto(patient));
        Assert.assertEquals(patient.getDoctorName(), patientService.patientToDto(patient).getDoctorName());
        System.out.println("after update user "+ patient.getDiseaseName());
    }

    @Test()
    @DisplayName("Get SlipById Test ")
    public void getSlipById(){

            int pId = 3;
            Patient  patient1 = new Patient(pId, "Disease1", "aiims", null, "crocin", "blood", "amit", null, null, null, null, null);
            doReturn(Optional.of(patient1)).when(patientRepo).findById(pId);
            System.out.println("check user id "+ pId);
            patientService.patientToDto(patient1);
            Assert.assertEquals(patient1.getPId(), patientService.patientToDto(patient1).getPId());
            System.out.println("check user id "+ patientService.patientToDto(patient1).getPId());

    }

    @Test
    @DisplayName("Delete Slip Test ")
    public void deleteSlip(){

        System.out.println("patient Get Id  "+patient.getPId());
        when(patientRepo.findById(patient.getPId())).thenReturn(Optional.of(patient));
        System.out.println("test user  "+ patientService.patientToDto(patient).getPId());
        patientService.deleteSlip(patient.getPId());
        patientRepo.deleteById(patient.getPId());
        System.out.println("after delete user id "+ patient.getPId());
    }

    @Test
    public void shouldDeletePatients_IfPatientsExistsAndHasNotDeletedBeforeInDatabase() {
        Optional<Patient> existingUser = Optional.of(patient);
        Mockito.when(patientRepo.findById(Mockito.any())).thenReturn(existingUser);
        int userId = 1;
        patientService.deleteSlip(userId);
        System.out.println("test "+ userId);
    }

    @Test
    @DisplayName("Get Patients Test ")
    public void getPatients(){
        User user = new User();
        when(patientRepo.getPatients(user)).thenReturn(Stream
                .of(new Patient(3, "Disease3", "aiims", null, "crocin", "blood", "amit", null, null, null, null, null),
                        new Patient(4, "Disease4", "aiims2", null, "crocin", "blood", "amit", null, null, null, null, null)).collect(Collectors.toList()));
        Assert.assertEquals(2, patientService.getPatients(user).size());
        System.out.println("check Get ALl Slips "+ patientService.getPatients(user).size());
    }

    @Test
    @DisplayName("Get Patients Test ")
    public void getAllSlips(){
        when(patientRepo.findAll()).thenReturn(Stream
                .of(new Patient(3, "Disease3", "aiims", null, "crocin", "blood", "amit", null, null, null, null, null),
                        new Patient(4, "Disease4", "aiims2", null, "crocin", "blood", "amit", null, null, null, null, null)).collect(Collectors.toList()));
        Assert.assertEquals(2, patientService.getAllSlips().size());

    }
}



