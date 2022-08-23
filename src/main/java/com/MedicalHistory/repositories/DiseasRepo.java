package com.MedicalHistory.repositories;

import com.MedicalHistory.entities.Diseas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiseasRepo extends JpaRepository<Diseas, Integer> {

}
