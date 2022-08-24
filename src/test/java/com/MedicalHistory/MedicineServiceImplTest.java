package com.MedicalHistory;

import com.MedicalHistory.entities.Medicine;
import com.MedicalHistory.payloads.MedicineDto;
import com.MedicalHistory.repositories.MedicineRepo;
import com.MedicalHistory.services.impl.MedicineServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineServiceImplTest {
    @Autowired
    private MedicineServiceImpl service;

    @MockBean
    private MedicineRepo repository;


    @Test
    @DisplayName("Create Medicine Data Test ")
    public void getCreateMedicineDataTest() {
        MedicineDto medicine = new MedicineDto(1, "Crocin");
        when(repository.save(any())).thenReturn(service.dtoToMedicine(medicine));
        assertEquals(medicine.getMId(), service.createMedicineData(medicine).getMId());

    }

    @Test
    @DisplayName("All Medicines Test ")
    public void getAllMedicinesTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(new Medicine(1, "Crocin"),
                        new Medicine(2, "Paracetamol")).collect(Collectors.toList()));
        assertEquals(2, service.getAllMedicines().size());
        System.out.println("check medicine " + service.getAllMedicines().size());

    }

    @Test
    @DisplayName("Search Medicine Test ")
    public void getSearchMedicineTest() {
        String name = "Paracetamol";
        Medicine medicine = new Medicine(1, "Crocin");
        Medicine medicine1 = new Medicine(2, "Paracetamol");
        doReturn(Arrays.asList(medicine, medicine1)).when(repository).findByNameContaining(name);
        List<Medicine> medicineDtos = service.searchMedicine(name);
        assertTrue(medicineDtos.size() == 2);
        Assertions.assertThat(name).isEqualTo(medicineDtos.get(1).getName());
    }

    @Test
    @DisplayName("All Medicine Test ")
    public void getAllMedicineTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(new Medicine(1, "Crocin"),
                        new Medicine(2, "Paracetamol")).collect(Collectors.toList()));
        assertEquals(2, service.getAllMedicines().size());
        System.out.println("check medicine " + service.allMedicine().size());

    }
}
