package com.MedicalHistory.repositories;

import com.MedicalHistory.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer> {

}
