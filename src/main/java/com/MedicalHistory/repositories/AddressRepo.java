package com.MedicalHistory.repositories;

import com.MedicalHistory.entities.Address;
import com.MedicalHistory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

    @Query("select p from Patient p WHERE p.user =:n")
    List<Address> getUser(@Param("n") User user);

    Optional<Address> findById(Integer id);
}
