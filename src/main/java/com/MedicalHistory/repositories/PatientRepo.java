package com.MedicalHistory.repositories;

import com.MedicalHistory.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Integer> {

}
