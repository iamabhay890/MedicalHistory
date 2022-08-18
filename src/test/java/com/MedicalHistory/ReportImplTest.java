package com.MedicalHistory;

import com.MedicalHistory.entities.Medicine;
import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.PatientRepo;
import com.MedicalHistory.services.impl.ExcelReportServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.in;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.common.ArgumentUtils.toByteArray;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportImplTest {

    @Autowired
    ExcelReportServiceImpl service;

    @MockBean
    PatientRepo patientRepo;


    @Test
    @DisplayName("Export Excel Test")
    public void getExportExcelTest(){

        Patient  patient = new Patient(1, "Disease1", "aiims", null, "crocin", "blood", "amit", null, null, null);
        Patient  patient1 = new Patient(2, "Disease2", "aiims2", null, "crocin", "blood", "amit", null, null, null);

        List<Patient> allPatient= Arrays.asList(patient,patient1);
        //when(patientRepo.findAll()).thenReturn((List<Patient>) service.export(allPatient));

        doReturn(allPatient).when(patientRepo).findAll();
        service.export(allPatient);
        System.out.println("test1 "+allPatient);

    }
}
