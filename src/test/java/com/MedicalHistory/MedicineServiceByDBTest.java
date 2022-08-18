package com.MedicalHistory;

import com.MedicalHistory.entities.Medicine;
import com.MedicalHistory.repositories.MedicineRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicineServiceByDBTest {

    @Autowired
    private MedicineRepo medicineRepo;

    @Test
    @Order(1)
    @Rollback
    @Transactional
    @DisplayName("Create Medicine Data Test ")
    public void createMedicineData(){
        Medicine medicine = Medicine.builder()
                .mId(33)
                .name("omg")
                .build();
                 medicineRepo.save(medicine);
    Assertions.assertThat(medicine.getMId()).isGreaterThan(1);
    }

    @Test
    @Order(2)
    @DisplayName("Get All Medicine Test ")
    public void getAllMedicines(){

        List<Medicine> medicines = medicineRepo.findAll();
        Assertions.assertThat(medicines.size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    @DisplayName("Search Medicine Test ")
    public void searchMedicine(){

        List<Medicine> medicines = medicineRepo.findByNameContaining("omg");
        Assertions.assertThat(medicines.equals("omg"));

    }

    @Test
    @Order(4)
    @DisplayName("All Medicine Test ")
    public void allMedicine(){

        List<Medicine> medicines = medicineRepo.findAll();
        Assertions.assertThat(medicines.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @DisplayName("findByNameContaining Test ")
    public void findByNameContaining(){

        List<Medicine> medicines = medicineRepo.findByNameContaining("omg");
        Assertions.assertThat(medicines.equals("omg"));
    }
}
