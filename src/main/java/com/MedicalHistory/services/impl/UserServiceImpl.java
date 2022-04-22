package com.MedicalHistory.services.impl;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.exceptions.ResourceNotFoundException;
import com.MedicalHistory.payloads.UserDto;
import com.MedicalHistory.repositories.UserRepo;
import com.MedicalHistory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user =this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto update(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
       // user.setAddress(userDto.getAddress());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());

        User updatedUser=this.userRepo.save(user);
        UserDto userDto1=this.userToDto(updatedUser);
        return userDto1;

    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
    User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

    this.userRepo.delete(user);


    }




    public User dtoToUser(UserDto userDto){

        User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        //user.setAddress(userDto.getAddress());
        user.setGender(userDto.getGender());
        user.setAge(userDto.getAge());

        return user;
    }

    public UserDto userToDto(User user){

        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        //userDto.setAddress(user.getAddress());
        userDto.setGender(user.getGender());
        userDto.setAge(userDto.getAge());
        return userDto;
    }
}
