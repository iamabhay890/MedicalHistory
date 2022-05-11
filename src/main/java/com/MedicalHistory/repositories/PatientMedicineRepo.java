package com.MedicalHistory.repositories;

import com.MedicalHistory.entities.PatientMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMedicineRepo extends JpaRepository<PatientMedicine,Integer> {

}