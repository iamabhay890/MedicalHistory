package com.MedicalHistory.services.impl;

import com.MedicalHistory.entities.Address;
import com.MedicalHistory.entities.User;
import com.MedicalHistory.exceptions.ResourceNotFoundException;
import com.MedicalHistory.payloads.AddressDto;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.AddressRepo;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public AddressDto createAddressData(AddressDto addressDto) {
        Address address = this.dtoToAddress(addressDto);
        Address savedAddress = this.addressRepo.save(address);
        return this.addressToDto(savedAddress);
    }

    @Override
    public AddressDto update(AddressDto addressDto, Integer aid) {

        Address address = this.addressRepo.findById(aid).orElseThrow(()
                -> new ResourceNotFoundException("Address", " slip ", aid));

        address.setAid(addressDto.getAid());
        address.setAddress1(addressDto.getAddress1());
        address.setAddress2(addressDto.getAddress2());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZip(addressDto.getZip());
        address.setCountry(addressDto.getCountry());
        Address updateAddress = this.addressRepo.save(address);
        AddressDto addressDto1 = this.addressToDto(updateAddress);
        return addressDto1;
    }

    @Override
    public List<Address> getUser(User user) {
        return this.addressRepo.getUser(user);
    }

    @Override
    public Optional<Address> findById(Integer id) {
        return addressRepo.findById(id);
    }

    public Address dtoToAddress(AddressDto addressDto) {
        UserDto userDto = new UserDto();
        User user = this.userRepo.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userDto.getId()));
        Address address = new Address();
        address.setAid(addressDto.getAid());
        address.setAddress1(addressDto.getAddress1());
        address.setAddress2(addressDto.getAddress2());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZip(addressDto.getZip());
        address.setCountry(addressDto.getCountry());
        address.setUser(addressDto.getUser());
        //address.setUser(user);
        System.out.println("test 1 ====> "+addressDto.getUser().getId());
        System.out.println("test 2 ====> "+user);

        return address;
    }

    public AddressDto addressToDto(Address address) {

        AddressDto addressDto = new AddressDto();
        addressDto.setAid(address.getAid());
        addressDto.setAddress1(address.getAddress1());
        addressDto.setAddress2(address.getAddress2());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setZip(address.getZip());
        addressDto.setCountry(address.getCountry());
        addressDto.setUser(addressDto.getUser());
        return addressDto;

    }
}
