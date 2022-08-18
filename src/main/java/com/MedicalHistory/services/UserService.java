package com.MedicalHistory.services;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);

    UserDto update(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

    public User getAdmin();

    public List<User> getUsers();

    public User findByEmail(String email);


    UserDto updatePassword(UserDto userDto);

    UserDto forgotPass(UserDto userDto);

    UserDto updatePic(UserDto userDto,Integer userId);
}
