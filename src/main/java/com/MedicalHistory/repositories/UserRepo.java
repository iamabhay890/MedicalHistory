package com.MedicalHistory.repositories;

import com.MedicalHistory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepo extends JpaRepository<User,Integer> {

    public User findByEmail(String email);


    @Query(value = "select * from users where not id='1'",nativeQuery = true)
    public List<User> getUsers();

    @Query(value = "select * from users where id='1'",nativeQuery = true)
    public User getAdmin();
}

