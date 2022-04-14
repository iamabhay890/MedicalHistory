package com.MedicalHistory.repositories;

import com.MedicalHistory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
