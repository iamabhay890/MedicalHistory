package com.MedicalHistory.repositories;

import com.MedicalHistory.controllers.PataientUController;
import com.MedicalHistory.entities.Patient;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer> {


    @Query("select p from Patient p WHERE p.user =:n")
    public List<Patient> getPatients(@Param("n") User user);
}