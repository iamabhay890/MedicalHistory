package com.MedicalHistory.repositories;

import com.MedicalHistory.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepo extends JpaRepository<Medicine, Integer> {

    List<Medicine> findByNameContaining(String query);
}
