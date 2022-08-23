package com.MedicalHistory.services;

import com.MedicalHistory.entities.User;
import com.MedicalHistory.payloads.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user, MultipartFile file);

    UserDto update(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

    User getAdmin();

    List<User> getUsers();

    User findByEmail(String email);


    UserDto updatePassword(UserDto userDto);

    UserDto forgotPass(UserDto userDto);

    void updateProfilePicture(MultipartFile file, Integer id);

    void createOAuth2User(String email, String fullName, String OauthEmail);


}
