package com.login.medical_history_login.repository;

import com.login.medical_history_login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String name);

}