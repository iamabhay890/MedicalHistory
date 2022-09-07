package com.MedicalHistory.services;
import com.MedicalHistory.entities.Address;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    AddressDto createAddressData(AddressDto addressDto);

    AddressDto update(AddressDto addressDto, Integer aid);

    List<Address> getUser(User user);

    Optional<Address> findById(Integer id);
}
